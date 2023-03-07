package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Order order;

}
