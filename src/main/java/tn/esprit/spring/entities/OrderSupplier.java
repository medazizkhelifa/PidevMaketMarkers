package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

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
public class OrderSupplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int IdOrder;
    int quantite;

    Date dateCreation;
    Date dateline;

    @ManyToOne(cascade = CascadeType.ALL)
    Supplier supplier;
    @OneToMany(cascade = CascadeType.ALL)
    Set<OrderLineSupplier> OderLineSuppliers;
}
