package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderLine;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.IOrderLineRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.serviceInterface.IHttpHelper;
import tn.esprit.spring.serviceInterface.IInvoiceService;
import tn.esprit.spring.serviceInterface.IOrderService;

import java.util.List;

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
    IHttpHelper httpHelper;

    @Override
    public void addOrder(Order order) throws Exception {

        this.validateOrderLineBody(order);

        this.validateOrderLinesQuantityVsProductStock(order);

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

    private void validateOrderLineBody(Order order) throws Exception {
        if (order.getOrderLines().size() == 0) {
            throw new Exception("Order can not be empty");
        }
        for (OrderLine orderLine : order.getOrderLines()) {
            if (orderLine.getQuantity() <= 0) {
                throw new Exception("Order line quantity must be greater than 0");
            }
            if (orderLine.getProduct() == null) {
                throw new Exception("Order line product must be set");
            }
        }
    }

    private boolean validateOrderLinesQuantityVsProductStock(Order order) throws Exception {
        for (OrderLine orderLine : order.getOrderLines()) {
            Long productCode = orderLine.getProduct().getCode();
            int quantity = orderLine.getQuantity();
            if (!checkAvailibility(productCode, quantity)) {
                throw new Exception("Product " + productCode + " is not available in stock");
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
