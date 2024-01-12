package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Compensation {

        public enum RefundStatus {
        NOT_ELIGIBLE,
        REFUNDED,
        PENDING,
        ELIGIBLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification", nullable = false)
    private int id;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "validateDate", nullable = false)
    private Date validateDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "ticketId", nullable = false)
    private int ticketId;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusRefund", nullable = false)
    private RefundStatus statusRefund;

    // Constructors
    public Compensation(int id, String client, String type, String detail, Date validateDate, double amount, int ticketId, RefundStatus statusRefund) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.detail = detail;
        this.validateDate = validateDate;
        this.amount = amount;
        this.ticketId = ticketId;
        this.statusRefund = statusRefund;
        //Because of the ValidDate, G30 always needs to send a request to Fidelity.
        // Additionally, the client's Fidelity status can change over time.
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

    public RefundStatus getStatusRefund() {
        return statusRefund;
    }

    public void setStatusRefund(RefundStatus statusRefund) {
        this.statusRefund = statusRefund;
    }
}
