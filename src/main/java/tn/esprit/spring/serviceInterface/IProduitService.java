package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.entities.Product;

import java.util.List;

public interface IProduitService {
    Product addProduct(Product product);

    List<Product> getProduct();

    void deleteProduct(Long id);

    Product updateProduct(Product product);
}
