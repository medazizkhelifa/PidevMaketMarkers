package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Delievery;

import java.util.List;

public interface ILivraisonService {




    Delievery addLivraison(Delievery livraison);
    List<Delievery> getLivraison();
    void deleteLivraison(Long id);
    Delievery updateLivraison(Delievery livraison);
}
