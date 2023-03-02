package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.esprit.spring.entities.Product;

@Component
public class StockAlert {
    @Autowired
    private ProductService productService;

    public void sendAlert(Product product) {
        if (productService.isLowInStock(product.getIdProduct())) {
            System.out.println("ALERT: Le produit " + product.getNom() + " est faible en stock.");
            // implémenter le code pour envoyer l'alerte (e-mail, SMS, etc.)
        }
    }
}
