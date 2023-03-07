package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;



@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderLine> orderLines ;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Invoice invoice;

    private OrderStatus status = OrderStatus.PENDING;

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

    // getters and setters
}