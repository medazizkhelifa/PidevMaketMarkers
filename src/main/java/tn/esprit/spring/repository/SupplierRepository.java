package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}