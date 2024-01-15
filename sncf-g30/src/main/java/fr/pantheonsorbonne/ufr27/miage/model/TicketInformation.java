package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_information")
public class TicketInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketIdInformation", nullable = false)
    private Integer ticketIdInformation;

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


    public Integer getTicketIdInformation() {
        return ticketIdInformation;
    }

    public void setTicketIdInformation(Integer ticketIdInformation) {
        this.ticketIdInformation = ticketIdInformation;
    }

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

}
