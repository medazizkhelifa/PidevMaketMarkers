package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Product;
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

    @GetMapping("/filtre")
    public List<Product> productsFiltred(
           @RequestParam(value = "nom" , required = false)  String nom , @RequestParam(value = "prix" , required = false) Double prix ,  @RequestParam(value = "idCatg" , required = false) Long idCatg) {
        return productService.productsFiltred(nom,prix,idCatg);
    }
/*
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importProductsJob;

    @PostMapping("/import")
    public ResponseEntity<String> importProducts() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("input.file.name", "products.xlsx")
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(importProductsJob, jobParameters);
        return ResponseEntity.ok("Import job started with ID: " + jobExecution.getJobId());
    }*/
}