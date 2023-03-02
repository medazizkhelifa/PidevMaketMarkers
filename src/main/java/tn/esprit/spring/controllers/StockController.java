package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/Stock")
public class StockController {
    @Autowired
    StockService stockService;

    @PutMapping("/{idProduct}/{quantite}")
    public void UpdateStock(@PathVariable Long idProduct, @PathVariable int quantite) {
        stockService.UpdateStock(idProduct,quantite);
    }

    @PostMapping("/add-Stock")
    @ResponseBody
    public Stock addStock(@RequestBody Stock s)
    {
        return stockService.addStock(s);

    }

    @GetMapping
    public List<Stock> getProduct() {
        return stockService.getStock();
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        stockService.deleteStock(id);
    }
    @PutMapping
    public Stock updateProduct(@RequestBody Stock Stock) {
        return stockService.updateStock(Stock);
    }


}
