package tn.esprit.spring.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    String userName;
    String Password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private List<Role> roles = new ArrayList<>();
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    private String email;
    public void setResetToken(String resetToken) {
        this.resetPasswordToken = resetToken;
    }



}