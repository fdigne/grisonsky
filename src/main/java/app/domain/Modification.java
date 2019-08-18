package app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Modification")
public class Modification implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Renter renter ;

    @Column(name="message")
    private String message;

    @Column(name="date")
    private Date date;

    public Modification() {
    }

    public Modification(Renter renter, String message, Date date) {
        this.renter = renter;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
