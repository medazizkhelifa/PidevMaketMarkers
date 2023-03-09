package tn.esprit.testReclamation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.testReclamation.Entities.Avis;

@Repository
public interface IAvis extends JpaRepository<Avis, Integer> {}

