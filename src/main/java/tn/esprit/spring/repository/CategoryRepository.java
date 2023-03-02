package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}