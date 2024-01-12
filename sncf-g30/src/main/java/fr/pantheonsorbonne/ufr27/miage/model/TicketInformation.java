package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
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


    public TicketInformation(Integer ticketId, Integer trainId, Integer trajetId, Double prix, Integer clientId) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.trajetId = trajetId;
        this.prix = prix;
        this.clientId = clientId;
    }
    public TicketInformation() {
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
