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
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TransactionMethod Type;
    @Enumerated(EnumType.STRING)
    private TransactionStatus Status;
    private String Description;
    private Date transactionDate;


    @OneToOne(fetch = FetchType.LAZY)
    private Invoice invoice;
}
