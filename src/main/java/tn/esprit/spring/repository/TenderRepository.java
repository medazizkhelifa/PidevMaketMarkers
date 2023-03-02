package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Tender;

public interface TenderRepository extends JpaRepository<Tender, Long> {
}