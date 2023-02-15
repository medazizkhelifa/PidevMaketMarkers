package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString

public class Tender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idOff;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    Operator operator;

    @ManyToMany
    Set<Supplier> Suppliers;

 /*   @ManyToOne
    Product product;*/
}
