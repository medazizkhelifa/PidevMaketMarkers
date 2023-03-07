package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.NotificationDto;
import tn.esprit.spring.dto.OrderFilterDto;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderLine;
import tn.esprit.spring.entities.OrderStatus;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.IOrderLineRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IHttpHelper;
import tn.esprit.spring.serviceInterface.INotificationService;
import tn.esprit.spring.serviceInterface.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderLineRepository orderLineRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IHttpHelper httpHelper;

    @Autowired
    private INotificationService notificationService;

    @Override
    public void addOrder(Order order) throws InvalidInputException {

        this.validateOrderLineBody(order);

        this.validateOrderLinesQuantityVsProductStock(order);

        this.validateClient(order);

        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        order.getOrderLines().forEach(orderLine -> {
            orderLine.setOrder(order);
            orderLineRepository.save(orderLine);
        });

        NotificationDto notificationDto = new NotificationDto(2L, "", String.valueOf(order.getId()),"New order", "A new order has been submitted");
        this.notificationService.sendNotificationToUser(notificationDto);
    }

    @Override
    public boolean deleteOrder(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Order getOrder(int id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private void validateClient(Order order) throws InvalidInputException {
        if (order.getClient() == null) {
            throw new InvalidInputException("Client is required");
        }

        if (order.getClient().getId() == 0) {
            throw new InvalidInputException("Client id is required");
        }

        Optional<User> client = userRepository.findById(order.getClient().getId());
        if (!client.isPresent()) {
            throw new InvalidInputException("Client not found");
        }
    }

    private void validateOrderLineBody(Order order) throws InvalidInputException {
        if (order.getOrderLines().size() == 0) {
            throw new InvalidInputException("Order can not be empty");
        }

        for (OrderLine orderLine : order.getOrderLines()) {
            if (orderLine.getQuantity() <= 0) {
                throw new InvalidInputException("Order line quantity must be greater than 0");
            }
            if (orderLine.getProduct() == null) {
                throw new InvalidInputException("Order line product must be set");
            }
            if (orderLine.getProduct().getCode() <= 0) {
                throw new InvalidInputException("Order line product id must be set");
            }

            Optional<Product> product = productRepository.findById(orderLine.getProduct().getCode());

            if (!product.isPresent()) {
                throw new InvalidInputException("Order line product not found");
            }
        }
    }

    private boolean validateOrderLinesQuantityVsProductStock(Order order) throws InvalidInputException {
        for (OrderLine orderLine : order.getOrderLines()) {
            Long productCode = orderLine.getProduct().getCode();
            int quantity = orderLine.getQuantity();
            if (!checkAvailibility(productCode, quantity)) {
                throw new InvalidInputException("Product " + productCode + " is not available in stock");
            }
        }
        return true;
    }

    private boolean checkAvailibility(Long code, int quantity) {
        List<OrderLine> list = orderLineRepository.findByProduct_Code(code);
        if (list.size() == 0) {
            Product product = productRepository.findById(code).get();
            return product.getSecurityStock() >= quantity;
        }
        int quantitySold = list.stream().mapToInt(OrderLine::getQuantity).sum();
        int availableQuantity = list.get(0).getProduct().getSecurityStock() - quantitySold;
        return availableQuantity - quantity >= 0;
    }

    @Override
    public void updateOrderStatus(int id, OrderStatus status) throws InvalidInputException {
        if (orderRepository.existsById(id)) {
            Order order = orderRepository.findById(id).get();
            
            String notificationTitle = "Order updated";
            String notificationBody = "";

            if(status == OrderStatus.PENDING){
                throw new InvalidInputException("Order status can not be updated to pending");
            }

            if(order.getStatus() == OrderStatus.CANCELED){
                throw new InvalidInputException("Order has been already canceled");
            }

            if(order.getStatus() == OrderStatus.APPROVED){
                throw new InvalidInputException("Order has been already approved");
            }

            order.setStatus(status);
            orderRepository.save(order);
            if(status == OrderStatus.APPROVED)
            {
                notificationBody = "Your order has been accepted";
            }
            else if(status == OrderStatus.CANCELED)
            {
                notificationBody = "Your order has been canceled";
            }
            NotificationDto notificationDto = new NotificationDto(order.getClient().getId(), "", String.valueOf(order.getId()),notificationTitle, notificationBody);
            this.notificationService.sendNotificationToUser(notificationDto);
            
            return;
        }
        throw new InvalidInputException("Order not found");
    }

    @Override
    public List<Order> filterOrder(OrderFilterDto filter) {
        System.out.println("orderStatus.getCreateDate() " + filter.getCreateDate());
        try{
            OrderStatus orderStatus = OrderStatus.valueOf(filter.getStatus().toUpperCase());
            return orderRepository.filterOrder(filter.getId(), orderStatus, filter.getCreateDate());
        }catch(NullPointerException ex){
            return orderRepository.filterOrder(filter.getId(), null, filter.getCreateDate());
        }
    }

}
