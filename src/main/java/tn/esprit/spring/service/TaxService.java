package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Tax;
import tn.esprit.spring.repository.TaxRepository;
import tn.esprit.spring.serviceInterface.ITaxService;

import java.util.List;

@Service
@Slf4j
public class TaxService implements ITaxService {

    @Autowired
    TaxRepository taxRepository;
    @Override
    public Tax addTax(Tax tax) {

        return taxRepository.save(tax);
    }
    @Override
    public List<Tax> getTax(){
        return taxRepository.findAll();
    }
    @Override
    public void deleteTax(Long id){
        taxRepository.deleteById(id);
    }
    @Override
    public Tax updateTax(Tax tax){return taxRepository.save(tax);
    }
}
