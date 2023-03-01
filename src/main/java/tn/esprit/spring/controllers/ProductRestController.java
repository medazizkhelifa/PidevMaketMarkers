package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.LivraisonService;
import tn.esprit.spring.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductRestController {

    @Autowired
    ProductService productService;



    @PostMapping("/add-Product")
    @ResponseBody
    public Product addProduct(@RequestBody Product p)
    {
        return productService.addProduct(p);

    }

    @GetMapping
    public List<Product> getProduct() {
        return productService.getProduct();
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }



}