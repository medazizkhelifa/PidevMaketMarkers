package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Tax;
import tn.esprit.spring.service.TaxService;

import java.util.List;

@RestController
@RequestMapping("/Tax")
public class TaxRestController {

    @Autowired
    TaxService taxService;



    @PostMapping("/add-Tax")
    @ResponseBody
    public Tax addTax(@RequestBody Tax t)
    {
        return taxService.addTax(t);

    }
    @GetMapping
    public List<Tax> getTax() {
        return taxService.getTax();
    }
    @DeleteMapping("/{id}")
    public void deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
    }
    @PutMapping
    public Tax updateTax(@RequestBody Tax tax) {
        return taxService.updateTax(tax);
    }



}