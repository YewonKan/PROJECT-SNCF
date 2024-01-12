package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;

import java.time.Instant;


@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTicket", nullable = false)
    private Integer idTicket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTrip", nullable = false)
    private Trip idTrip;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idCustomer", nullable = true)
    private Customers idCustomers;


    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Trip getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Trip idTrip) {
        this.idTrip = idTrip;
    }

    public Customers getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(Customers idCustomers) {
        this.idCustomers = idCustomers;
    }
}