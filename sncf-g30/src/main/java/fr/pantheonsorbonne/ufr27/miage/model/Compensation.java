package fr.pantheonsorbonne.ufr27.miage.model;

import java.util.Date;

public class Compensation {

    private int id;
    private String client;
    private String type;
    private String detail;
    private Date validateDate;
    private double amount; // New property
    private int ticketId; // New property

    // Constructors
    public Compensation(int id, String client, String type, String detail, Date validateDate, double amount, int ticketId) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.detail = detail;
        this.validateDate = validateDate;
        this.amount = amount;
        this.ticketId = ticketId;
    }

    public Compensation() {
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
