package tn.esprit.spring.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Supplier;
import tn.esprit.spring.repository.SupplierRepository;
import tn.esprit.spring.serviceInterface.ISupplierService;

import java.util.List;

@Service
@Slf4j
public class SupplierService implements ISupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public void deleteSupplier(int id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}
