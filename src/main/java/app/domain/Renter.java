package app.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Renter")
public class Renter implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;


    @Column(name="appartments")
    private String appartments;


    public Renter() {
    }

    public Renter(String name, String firstName, String phoneNumber, String appartments) {
        this.name = name;
        this.appartments = appartments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppartments() {
        return appartments;
    }

    public void setAppartments(String appartments) {
        this.appartments = appartments;
    }
}
