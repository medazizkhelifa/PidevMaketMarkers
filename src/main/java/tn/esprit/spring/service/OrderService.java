package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderLine;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.IClientRepository;
import tn.esprit.spring.repository.IOrderLineRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.serviceInterface.IHttpHelper;
import tn.esprit.spring.serviceInterface.IInvoiceService;
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
    IInvoiceService invoiceService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IClientRepository clientRepository;

    @Autowired
    IHttpHelper httpHelper;

    @Override
    public void addOrder(Order order) throws InvalidInputException {

        this.validateOrderLineBody(order);

        this.validateOrderLinesQuantityVsProductStock(order);

        this.validateClient(order);

        orderRepository.save(order);

        order.getOrderLines().forEach(orderLine -> {
            orderLine.setOrder(order);
            orderLineRepository.save(orderLine);
        });

        this.invoiceService.generateInvoice(order);
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

        Optional<Client> client = clientRepository.findById(order.getClient().getId());
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

}
