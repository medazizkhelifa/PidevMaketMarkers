package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Order;
@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
//find by product
    public Order findByOrderLines_Product_Code(Long code);
    //check product availability
    public boolean existsByOrderLines_Product_Code(Long code);
    //find by invoice
    public Order findByInvoice_Id(int id);
}

