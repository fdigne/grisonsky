package app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="cleaning", columnDefinition="Integer default '0'")
    private int cleaning;

    @Column(name="service", columnDefinition="Decimal(10,2) default '0'")
    private Double service;

    @Column(name="lastPaid")
    private Date lastPaid;

    public Bill() {
    }

    public Bill(int cleaning, Double service, Date lastPaid) {
        this.cleaning = cleaning;
        this.service = service;
        this.lastPaid = lastPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCleaning() {
        return cleaning;
    }

    public void setCleaning(int cleaning) {
        this.cleaning = cleaning;
    }

    public Double getService() {
        return service;
    }

    public void setService(Double service) {
        this.service = service;
    }

    public Date getLastPaid() {
        return lastPaid;
    }

    public void setLastPaid(Date lastPaid) {
        this.lastPaid = lastPaid;
    }
}
