package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderSupplier;
import tn.esprit.spring.service.OrderSupplierService;
import tn.esprit.spring.serviceInterface.IOrderSupplierService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersSupplier")
public class OrderSupplierController {

    @Autowired
    OrderSupplierService orderSupplierService;

    @PostMapping("/{supplierId}/{produitsQuantites}")
    public void addOrder(@PathVariable int supplierId,@RequestBody Map<Long, Integer> produitsQuantites){
        orderSupplierService.passerCommande(supplierId,produitsQuantites);
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<OrderSupplier>> getAllOrders(){
        List<OrderSupplier> orderSuppliers = orderSupplierService.getAllOrdersSuppliers();
        if(orderSuppliers.size() > 0){
            return new ResponseEntity<List<OrderSupplier>>(orderSuppliers, HttpStatus.OK);
        }
        return new ResponseEntity<List<OrderSupplier>>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int id){
        if(orderSupplierService.deleteOrderSupplier(id)) {
            return new ResponseEntity("Order Deleted ", HttpStatus.OK);
        }
        return new ResponseEntity("Order Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<OrderSupplier> getOrder(@PathVariable("id") int id){
        OrderSupplier orderSupplier = orderSupplierService.getOrderSupplier(id);
        if(orderSupplier != null){
            return new ResponseEntity<OrderSupplier>(orderSupplier, HttpStatus.OK);
        }
        return new ResponseEntity<OrderSupplier>(HttpStatus.NOT_FOUND);
    }
}
