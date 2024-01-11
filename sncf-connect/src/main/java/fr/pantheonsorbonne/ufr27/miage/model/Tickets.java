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
    private Customers idTrip;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idCustomer", nullable = true)
    private Customers idCustomers;

    public Tickets(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Tickets() {
    }

    @Column(nullable = true)
    private Instant validUntil;

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    @Column(nullable = true)
    private String seatReference;

    public String getTicketKey() {
        return ticketKey;
    }

    public void setTicketKey(String ticketKey) {
        this.ticketKey = ticketKey;
    }

    private String ticketKey;


    public String getSeatReference() {
        return seatReference;
    }

    public void setSeatReference(String seatReference) {
        this.seatReference = seatReference;
    }

    public void setIdTrip(Trip trip) {
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }



    public void setTrip(Trip trip) {

    }

    public void setCustomer(Customers customers) {
    }

    public Customers getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Customers idTrip) {
        this.idTrip = idTrip;
    }

    public Customers getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(Customers idCustomers) {
        this.idCustomers = idCustomers;
    }
}