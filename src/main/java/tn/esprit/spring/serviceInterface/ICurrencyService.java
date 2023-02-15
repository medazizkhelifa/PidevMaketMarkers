package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Currency;

import java.util.List;

public interface ICurrencyService {
    Currency addCurrency(Currency currency);

    List<Currency> getCurrency();

    void deleteCurrency(Long id);

    Currency updateCurrency(Currency currency);
}
