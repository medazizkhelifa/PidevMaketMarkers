package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.service.TenderService;

import java.util.List;

@RestController
public class TenderController {
    @Autowired
    private TenderService tenderService;

    @PostMapping("/Tender/{produitId}/{quantiteRequise}")
    public void lancerAppelOffre(@PathVariable Long produitId, @PathVariable int quantiteRequise) {
        tenderService.lancerAppelOffre(produitId, quantiteRequise);
    }

}
