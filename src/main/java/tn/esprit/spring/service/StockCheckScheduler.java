package tn.esprit.spring.service;

import antlr.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.entities.Product;

@Component
public class StockCheckScheduler {
    @Autowired
    private StockAlert stockAlert;
    @Autowired
    private ProductService productService;

    @Scheduled(fixedDelay = 3600000) // vérifier le stock toutes les heures
    public void checkStock() {


        // récupérer tous les produits

        java.util.List<Product> products = productService.getProduct();

        // vérifier chaque produit et envoyer une alerte si nécessaire
        for (Product product : products) {
            stockAlert.sendAlert(product);
        }
    }
}
