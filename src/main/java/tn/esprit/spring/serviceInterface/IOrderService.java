package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.entities.Order;

import java.util.List;

public interface IOrderService {
    void addOrder(Order order) throws InvalidInputException;
    boolean deleteOrder(int id);
    Order getOrder(int id);
    List<Order> getAllOrders();
}
