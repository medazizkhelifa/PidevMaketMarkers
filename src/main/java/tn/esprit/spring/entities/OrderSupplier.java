package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class OrderSupplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdLigneCmd;
    private int quantite;
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderLineSupplier> OderLineSuppliers;
}
