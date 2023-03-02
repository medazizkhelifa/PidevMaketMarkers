package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String symbol;
    String code;
  /*  @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<Product> products;
*/
  @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Product> products;
}