package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "compensation")
public class Compensation {

        public enum RefundStatus {
        NOT_ELIGIBLE,
        REFUNDED,
        PENDING,
        ELIGIBLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", nullable = false)
    private int ticketId;

    @Column(name = "client", nullable = false)
    private int client;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "validateDate", nullable = false)
    private Date validateDate;

    @Column(name = "amount", nullable = false)
    private double amount;



    @Enumerated(EnumType.STRING)
    @Column(name = "statusRefund", nullable = false)
    private RefundStatus statusRefund;


    @Column(name = "requestDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    public Compensation(int ticketId, int client, String type, String detail, Date validateDate, double amount, RefundStatus statusRefund) {
        this.ticketId = ticketId;
        this.client = client;
        this.type = type;
        this.detail = detail;
        this.validateDate = validateDate;
        this.amount = amount;
        this.statusRefund = statusRefund;
    }
        //Because of the ValidDate, G30 always needs to send a request to Fidelity.
        // Additionally, the client's Fidelity status can change over time.


    public Compensation() {
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
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
