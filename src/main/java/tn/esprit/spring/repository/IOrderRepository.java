package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Order;
import tn.esprit.spring.entities.OrderStatus;
@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
//find by product
    public Order findByOrderLines_Product_Code(Long code);
    //check product availability
    public boolean existsByOrderLines_Product_Code(Long code);
    //find by invoice
    public Order findByInvoice_Id(int id);
    @Query("SELECT o FROM Order o WHERE (:status is null OR o.status = :status) AND (:id is null OR o.id = :id) AND (:date is null OR o.createdAt >= :date)")
    List<Order> filterOrder(@Param("id") int id, @Param("status") OrderStatus status, @Param("date") Date createdDate);
}

