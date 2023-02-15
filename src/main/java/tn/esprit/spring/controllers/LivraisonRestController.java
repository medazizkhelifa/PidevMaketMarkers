package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.service.LivraisonService;

import java.util.List;

@RestController
@RequestMapping("/Livraison")
public class LivraisonRestController {

    @Autowired
    LivraisonService livraisonService;



    @PostMapping("/add-Livraison")
    @ResponseBody
    public Delievery addLivraison(@RequestBody Delievery l)
    {
        return livraisonService.addLivraison(l);

    }

    @GetMapping
    public List<Delievery> getLivraison() {
        return livraisonService.getLivraison();
    }
    @DeleteMapping("/{id}")
    public void deleteLivraison(@PathVariable Long id) {
        livraisonService.deleteLivraison(id);
    }
    @PutMapping
    public Delievery updateLivraison(@RequestBody Delievery livraison) {
        return livraisonService.updateLivraison(livraison);
    }



}
