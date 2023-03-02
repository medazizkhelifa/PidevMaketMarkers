package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            "from Product p where " +
                    ":nom is null or p.nom = :nom " +
                    "and :prix is null or p.prix = :prix " +
                    "and p.catogory.idCatg is null or p.catogory.idCatg = :idCatg order by p.prix"
    )
    public List<Product> productsFiltred (@Param("nom") String nom , @Param("prix") Double prix , @Param("idCatg") Long idCatg);



}

