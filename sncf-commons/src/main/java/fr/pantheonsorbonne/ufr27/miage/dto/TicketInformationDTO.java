package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TicketInformationDTO {
    private Integer ticketId;
    private Integer trainId;
    private Integer trajetId;
    private Double prix;
    private Integer clientId;

    public TicketInformationDTO() {
    }

    public TicketInformationDTO(Integer ticketId, Integer trainId, Integer trajetId, Double prix, Integer clientId) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.trajetId = trajetId;
        this.prix = prix;
        this.clientId = clientId;
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
