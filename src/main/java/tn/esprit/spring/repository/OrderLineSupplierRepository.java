package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.OrderLineSupplier;

public interface OrderLineSupplierRepository extends JpaRepository<OrderLineSupplier, Integer> {
}