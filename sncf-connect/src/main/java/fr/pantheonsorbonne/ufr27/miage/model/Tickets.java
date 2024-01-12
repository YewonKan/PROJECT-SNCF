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

    @JoinColumn(name = "fname", nullable = true)
    private String fname;
    @JoinColumn(name = "lname", nullable = true)
    private String lname;

    @JoinColumn(name = "email", nullable = true)
        private String email;

    @JoinColumn(name = "phone", nullable = true)
    private String phone;

    @JoinColumn(name = "prix", nullable = true)
    private Float prix;

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}



