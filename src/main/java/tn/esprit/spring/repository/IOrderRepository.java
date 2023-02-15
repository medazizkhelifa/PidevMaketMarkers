package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Order;
@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

}

