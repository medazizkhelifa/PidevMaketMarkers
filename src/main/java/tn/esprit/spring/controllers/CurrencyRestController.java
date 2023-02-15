package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.service.CurrencyService;
import tn.esprit.spring.service.LivraisonService;

import java.util.List;

@RestController
@RequestMapping("/Currency")
public class CurrencyRestController {

    @Autowired
    CurrencyService currencyService;



    @PostMapping("/add-Currency")
    @ResponseBody
    public Currency addCurrency(@RequestBody Currency currency)
    {
        return currencyService.addCurrency(currency);

    }

    @GetMapping
    public List<Currency> getCurrency() {
        return currencyService.getCurrency();
    }
    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
    }
    @PutMapping
    public Currency updateCurrency(@RequestBody Currency currency) {
        return currencyService.updateCurrency(currency);
    }



}