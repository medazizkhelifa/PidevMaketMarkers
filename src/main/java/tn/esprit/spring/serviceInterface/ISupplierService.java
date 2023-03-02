package tn.esprit.spring.serviceInterface;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Supplier;

import java.util.List;

public interface ISupplierService {
    Supplier addSupplier(Supplier supplier);

    List<Supplier> getSupplier();

    void deleteSupplier(int id);

    public Supplier updateSupplier(Supplier supplier);

}
