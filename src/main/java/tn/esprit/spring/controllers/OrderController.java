package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.serviceInterface.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        if(orders.size() > 0){
            return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int id){
        if(orderService.deleteOrder(id)) {
            return new ResponseEntity("Order Deleted ", HttpStatus.OK);
        }
        return new ResponseEntity("Order Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id){
        Order order = orderService.getOrder(id);
        if(order != null){
            return new ResponseEntity<Order>(order, HttpStatus.OK);
        }
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }
}
