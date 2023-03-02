package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.serviceInterface.IProduitService;

import java.util.List;

@Service
@Slf4j
public class ProductService implements IProduitService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    @Override
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public List<Product> productsFiltred(String nom, Double prix, Long idCatg) {
        return productRepository.productsFiltred(nom,prix,idCatg);
    }

    /*
    public void saveProducts(List<Product> products) {
        productRepository.saveAll(products);
    }*/


    private static final int LOW_STOCK_THRESHOLD = 10;

    @Autowired
    private StockRepository stockRepository;

    public boolean isLowInStock(Long productId) {
        List<Stock> stocks = stockRepository.findByProduct_idProduct(productId);
        int totalStock = stocks.stream().mapToInt(Stock::getStock).sum();
        return totalStock < LOW_STOCK_THRESHOLD;
    }
}
