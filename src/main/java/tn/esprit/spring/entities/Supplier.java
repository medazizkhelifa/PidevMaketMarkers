package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@Entity
public class Supplier extends User {

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    private Set<OrderSupplier> orderSuppliers;
    @ManyToMany
    Set<Tender> tenders;

    @OneToMany(mappedBy = "supplier")
    Set<Offre>  offres;
}