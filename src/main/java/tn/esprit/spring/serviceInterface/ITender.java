package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.Supplier;

import java.util.List;

public interface ITender {
    public void lancerAppelOffre(Long produitId, int quantiteRequise);
}
