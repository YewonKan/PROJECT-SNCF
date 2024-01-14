package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_information")
public class TicketInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", nullable = false)
    private Integer ticketId;

    @Column(name = "trainId", nullable = false)
    private Integer trainId;

    @Column(name = "trajetId", nullable = false)
    private Integer trajetId;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "clientId", nullable = false)
    private Integer clientId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "lname", nullable = false)
    private String lname;

    @Column(name = "phone", nullable = false)
    private String phone;


    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(Integer trajetId) {
        this.trajetId = trajetId;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
