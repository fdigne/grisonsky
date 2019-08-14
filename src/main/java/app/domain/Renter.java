package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Renter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Renter implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;


    @Column(name="appartments")
    private String appartments;

    @Column(name="admin")
    private boolean admin;

    public Renter() {
    }

    public Renter(String name, String appartments, boolean admin) {
        this.name = name;
        this.appartments = appartments;
        this.admin = admin;
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

    public void setAppartments(String appartments)
    {
        this.appartments = appartments;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
