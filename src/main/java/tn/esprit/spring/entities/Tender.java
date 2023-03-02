package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private int quantiteRequise;
    private LocalDate dateLimite;


    @ManyToMany
    @JoinTable(name = "appel_offre_fournisseur",
            joinColumns = @JoinColumn(name = "appel_offre_id"),
            inverseJoinColumns = @JoinColumn(name = "fournisseur_id"))
    private List<Supplier> suppliers;


    @ManyToOne
    Product product;







    @OneToMany(mappedBy = "tender")
    Set<Offre> offres;


        /*
    @ManyToOne(cascade = CascadeType.ALL)
    Operator operator;*/

}
