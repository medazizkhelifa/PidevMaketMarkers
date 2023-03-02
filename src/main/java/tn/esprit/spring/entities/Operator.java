package tn.esprit.spring.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity

public class Operator extends User{
    /*
    @OneToMany(mappedBy = "operator",cascade = CascadeType.ALL)
    private Set<Tender> tender;*/
}
