package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
@ToString
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue
     int id;
    @CreatedDate
     Instant creationDate;
    @LastModifiedDate
     Instant LastUpdateDate;
     String FirstName;
     String LastName;
     Integer Num;
     String Email;
     String Password;



}