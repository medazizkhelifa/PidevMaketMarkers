package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Currency;
import tn.esprit.spring.repository.CurrencyRepository;
import tn.esprit.spring.serviceInterface.ICurrencyService;

import java.util.List;

@Service
@Slf4j
public class CurrencyService implements ICurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;
    @Override
    public Currency addCurrency(Currency currency) {

        return currencyRepository.save(currency);
    }
    @Override
    public List<Currency> getCurrency(){
        return currencyRepository.findAll();
    }

    @Override
    public void deleteCurrency(Long id){
        currencyRepository.deleteById(id);
    }
    @Override
    public Currency updateCurrency(Currency currency){
        return currencyRepository.save(currency);
    }
}