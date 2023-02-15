package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Delievery;
import tn.esprit.spring.repository.LivraisonRepository;
import tn.esprit.spring.serviceInterface.ILivraisonService;

import java.util.List;


@Service
@Slf4j
public class LivraisonService implements ILivraisonService {

    @Autowired
    LivraisonRepository livraisonRepository;
    @Override
    public Delievery addLivraison(Delievery livraison) {

        return livraisonRepository.save(livraison);
    }
    @Override
    public List<Delievery> getLivraison(){
        return livraisonRepository.findAll();
    }

@Override
    public void deleteLivraison(Long id){
        livraisonRepository.deleteById(id);
    }
    @Override
    public Delievery updateLivraison(Delievery livraison){
        return livraisonRepository.save(livraison);
    }
}

