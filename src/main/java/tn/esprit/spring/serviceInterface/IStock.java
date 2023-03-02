package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;

import java.util.List;

public interface IStock {
    Stock addStock(Stock stock);

    List<Stock> getStock();

    void deleteStock(Long id);

    Stock updateStock(Stock stock);
    void UpdateStock(Long idProduct, int quantite);
}
