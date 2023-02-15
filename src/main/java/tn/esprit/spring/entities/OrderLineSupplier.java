package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class OrderLineSupplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int ref;
    private Date orderDate;
    private Date reception;

    /*@ManyToOne(cascade = CascadeType.ALL)
    Product product;*/

    @ManyToOne(cascade = CascadeType.ALL)
    OrderSupplier orderSupplier;
}
