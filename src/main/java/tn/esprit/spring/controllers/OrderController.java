package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.config.InvalidInputException;
import tn.esprit.spring.dto.ApiResponse;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.serviceInterface.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/")
    public ApiResponse addOrder(@RequestBody Order order){
        ApiResponse apiResponse = new ApiResponse();
        try
        {
            orderService.addOrder(order);
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Order created successfully");
        }
        catch (InvalidInputException e)
        {
            apiResponse.setStatus("ERROR");
            apiResponse.setMessage("Error while creating order");
            apiResponse.setError(e.getMessage());
        }
        return apiResponse;
    }
    @GetMapping("/")
    @ResponseBody
    public ApiResponse getAllOrders(){
        ApiResponse apiResponse = new ApiResponse();
        List<Order> orders = orderService.getAllOrders();
        if(orders.size() > 0){
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Orders found");
            apiResponse.setData(orders);
            return apiResponse;
        }
        apiResponse.setStatus("ERROR");
        apiResponse.setMessage("No orders found");
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse deleteOrder(@PathVariable("id") int id){
        ApiResponse apiResponse = new ApiResponse();
        if(orderService.deleteOrder(id)) {
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Order deleted successfully");
            return apiResponse;
        }

        apiResponse.setStatus("ERROR");
        apiResponse.setMessage("Error while deleting order");
        return apiResponse;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ApiResponse getOrder(@PathVariable("id") int id){
        ApiResponse apiResponse = new ApiResponse();
        Order order = orderService.getOrder(id);
        if(order != null){
            apiResponse.setStatus("SUCCESS");
            apiResponse.setMessage("Order found");
            apiResponse.setData(order);
            return apiResponse;
        }
        apiResponse.setStatus("ERROR");
        apiResponse.setMessage("Order not found");
        return apiResponse;
    }
}
