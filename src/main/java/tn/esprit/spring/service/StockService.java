package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Stock;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.serviceInterface.IStock;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class StockService implements IStock {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStock(Long id) {
       stockRepository.deleteById(id);
    }

    @Override
    public Stock updateStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void UpdateStock(Long idProduct, int quantite) {
      stockRepository.UpdateStock(idProduct,quantite);
    }
}
