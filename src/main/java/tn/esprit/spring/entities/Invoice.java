package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
     Long Id;
    @Temporal(TemporalType.DATE)
     Date CreatedAt = new Date();
    @Temporal(TemporalType.DATE)
     Date UpdatedAt = new Date();
     double Subtotal;
     double Total;
     double Tax;
     double TaxPercent;
     double Shipping;
//this should be
}