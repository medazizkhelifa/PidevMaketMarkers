package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.repository.IOrderLineRepository;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.serviceInterface.IOrderService;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderLineRepository orderLineRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
        order.getOrderLines().forEach(orderLine -> {
            orderLine.setOrder(order);
            orderLineRepository.save(orderLine);
        });
    }

    @Override
    public boolean deleteOrder(int id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Order getOrder(int id) {
        if(orderRepository.existsById(id)){
            return orderRepository.findById(id).get();
        }
        return null;
    }
    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}

