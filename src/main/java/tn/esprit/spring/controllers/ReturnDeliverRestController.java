package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.entities.ReturnDelivery;
import tn.esprit.spring.service.LivraisonService;
import tn.esprit.spring.service.ReturnDeliveryService;

import java.util.List;

@RestController
@RequestMapping("/ReturnDelievery")
public class ReturnDeliverRestController {

    @Autowired
    ReturnDeliveryService returnDeliveryService;



    @PostMapping("/add-returndelivery")
    @ResponseBody
    public ReturnDelivery addreturndelivery(@RequestBody ReturnDelivery returnDelivery)
    {
        return returnDeliveryService.addReturnDelivery(returnDelivery);

    }

    @GetMapping
    public List<ReturnDelivery> getReturnDelivery() {
        return returnDeliveryService.getReturnDelivery();
    }
    @DeleteMapping("/{id}")
    public void deleteReturnDelivery(@PathVariable Long id) {
        returnDeliveryService.deleteReturnDelivery(id);
    }
    @PutMapping
    public ReturnDelivery updateReturnDelivery(@RequestBody ReturnDelivery returnDelivery) {
        return returnDeliveryService.updateReturnDelivery(returnDelivery);
    }



}