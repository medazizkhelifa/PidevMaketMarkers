package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Supplier;
import tn.esprit.spring.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/Supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping("/add-Supplier")
    @ResponseBody
    public Supplier addSupplier(@RequestBody Supplier s)
    {
        return supplierService.addSupplier(s);

    }

    @GetMapping
    public List<Supplier> getSupplier() {
        return supplierService.getSupplier();
    }
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable int id) {
        supplierService.deleteSupplier(id);
    }
    @PutMapping
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

}
