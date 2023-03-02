package tn.esprit.spring.service;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.ProductRepository;

import java.util.List;

public class ProductItemWriter implements ItemWriter<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        productRepository.saveAll(products);
    }
}
