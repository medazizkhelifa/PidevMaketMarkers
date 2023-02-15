package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long code;
    String description;
    String nom;
    String image;
    float prix;
    int SecurityStock;
   /* @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<OrderLine> OrderLines;
    @ManyToOne(cascade = CascadeType.ALL)
    Category catogory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<OrderLineSupplier> orderLineSuppliers;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Tender> tenders;
    @ManyToOne(cascade = CascadeType.ALL)
    Unit unit;
    @ManyToOne(cascade = CascadeType.ALL)
    Currency currency;*/
   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
   private List<OrderLine> orderLines = new ArrayList<>();
}