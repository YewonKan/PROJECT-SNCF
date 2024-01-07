package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Date;

public class Ticket {
    int ticketId;
    int clientId;
    Date departureDate;
    float ticketPrice;

    public Ticket(int ticketId, int clientId, Date departureDate, float ticketPrice) {
        this.ticketId = ticketId;
        this.clientId = clientId;
        this.departureDate = departureDate;
        this.ticketPrice = ticketPrice;
    }

    public Ticket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}