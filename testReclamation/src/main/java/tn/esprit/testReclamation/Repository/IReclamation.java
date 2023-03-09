package tn.esprit.testReclamation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.testReclamation.Entities.Reclamation;

@Repository
public interface IReclamation extends JpaRepository<Reclamation, Integer> {}
