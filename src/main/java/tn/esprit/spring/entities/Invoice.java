package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date CreatedAt;
    @Temporal(TemporalType.DATE)
    private Date UpdatedAt;
    private double Subtotal;
    private double Total;
    private double Tax;
    private double TaxPercent;
    private double Shipping;
    //this should be pointing on the Currency model(devise)
    private String Currency;
    private Date invoiceDate;

    @OneToOne(mappedBy = "invoice",fetch = FetchType.LAZY)
    private Transaction transaction;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;




}
