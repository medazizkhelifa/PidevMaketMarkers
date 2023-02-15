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

public class OrderClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

     Long ref;
    @CreatedDate
     Date orderDate;
     Date reception;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;
}
