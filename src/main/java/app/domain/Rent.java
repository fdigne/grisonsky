package app.domain;

import app.domain.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Rent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rent implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Client client;

    @OneToOne
    private Period period;

    @Column(name="nb_client")
    private int nbClient;

    @Column(name="cleaning")
    private boolean cleaning;

    @Column(name="site")
    private String site;

    @Column(name="price")
    private float price;

    @Column(name="comments")
    private String comments;

    @ManyToOne
    private Renter renter ;

    public Rent(Client client, Period period, int nbClient, boolean cleaning, String site, float price, String comments, Renter renter) {
        this.client = client;
        this.period = period;
        this.nbClient = nbClient;
        this.cleaning = cleaning;
        this.site = site;
        this.price = price;
        this.comments = comments;
        this.renter = renter;
    }

    public Rent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public app.domain.Client getClient() {
        return client;
    }

    public void setClient(app.domain.Client client) {
        this.client = client;
    }

    public app.domain.Period getPeriod() {
        return period;
    }

    public void setPeriod(app.domain.Period period) {
        this.period = period;
    }

    public int getNbClient() {
        return nbClient;
    }

    public void setNbClient(int nbClient) {
        this.nbClient = nbClient;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }
}
