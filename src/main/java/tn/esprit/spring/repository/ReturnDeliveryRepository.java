package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.ReturnDelivery;

@Repository
public interface ReturnDeliveryRepository extends JpaRepository<ReturnDelivery, Long> {

}
