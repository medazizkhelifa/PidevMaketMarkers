package tn.esprit.spring.serviceInterface;

import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.entities.Product;

import java.util.List;

public interface IProduitService {
    Product addProduct(Product product);

    List<Product> getProduct();

    void deleteProduct(Long id);

    Product updateProduct(Product product);


    List<Product> productsFiltred(String nom ,Double prix ,  Long idCatg);

   // public void saveProducts(List<Product> products);



}
