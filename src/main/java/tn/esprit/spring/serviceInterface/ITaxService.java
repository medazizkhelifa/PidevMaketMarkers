package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Tax;

import java.util.List;

public interface ITaxService {

    Tax addTax(Tax tax);


    List<Tax> getTax();

    void deleteTax(Long id);

    Tax updateTax(Tax tax);
}
