package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@NamedQueries({ @NamedQuery(name = "getDelayedTimeForTicketId", query = "SELECT d FROM DelayInformation d WHERE d.idTicket = ?1") })
@Entity
public class DelayInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification", nullable = false)
    private Integer id;

    @Column(name = "idTicket", nullable = false)
    private Integer idTicket;

    @Column(name = "delayedMinutes", nullable = false)
    private Integer delayedMinutes;

    @Column(name = "delayMotivation", nullable = false, length = 100)
    private String delayMotivation;
    @Column(name = "refundStatus", nullable = false)
    private boolean refunded;

    public DelayInformation(Integer idTicket, Integer delayedMinutes, String delayMotivation, boolean refunded) {
        this.idTicket = idTicket;
        this.delayedMinutes = delayedMinutes;
        this.delayMotivation = delayMotivation;
        this.refunded = refunded;
    }

    public DelayInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getDelayedMinutes() {
        return delayedMinutes;
    }

    public void setDelayedMinutes(Integer delayedMinutes) {
        this.delayedMinutes = delayedMinutes;
    }

    public String getDelayMotivation() {
        return delayMotivation;
    }

    public void setDelayMotivation(String delayMotivation) {
        this.delayMotivation = delayMotivation;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }
}
