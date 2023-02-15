package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Tax;


@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {


}
