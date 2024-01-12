package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class RefundRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refundRequestId", nullable = false)
    private Integer refundRequestId;

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

    @Column(name = "requestDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    public RefundRequest(Integer ticketId, Integer trainId, Integer trajetId, Double prix, Integer clientId, Date requestDate) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.trajetId = trajetId;
        this.prix = prix;
        this.clientId = clientId;
        this.requestDate = requestDate;
    }
    public RefundRequest() {
    }

    public Integer getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(Integer refundRequestId) {
        this.refundRequestId = refundRequestId;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

  }
