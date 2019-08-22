package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Renter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Renter implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="appartments")
    private String appartments;

    @Column(name="admin")
    private boolean admin;

    @OneToOne
    private Bill bill;

    public Renter() {
    }

    public Renter(String name, String appartments, boolean admin, Bill bill) {
        this.name = name;
        this.appartments = appartments;
        this.admin = admin;
        this.bill = bill;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
