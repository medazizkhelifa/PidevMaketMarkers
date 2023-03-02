package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.OrderLine;

import java.util.List;

@Repository
public interface IOrderLineRepository extends JpaRepository<OrderLine, Integer> {
    public List<OrderLine> findByProduct_Code(Long code);
}

