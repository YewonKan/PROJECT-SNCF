package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;

@Entity
public class DelayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDelayNotification", nullable = false)
    private Long idDelayNotification;

    @Column(name = "idTrain", nullable = false)
    private String idTrain;

    @Column(name = "delayDuration", nullable = false)
    private int delayDuration;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "creationTime", nullable = false)
    private String creationTime;



    public DelayNotification(String idTrain, int delayDuration, String reason, String creationTime) {
        this.idTrain = idTrain;
        this.delayDuration = delayDuration;
        this.reason = reason;
        this.creationTime = creationTime;
    }

    public DelayNotification() {
    }
    public Long getIdDelayNotification() {
        return idDelayNotification;
    }

    public void setIdDelayNotification(Long idDelayNotification) {
        this.idDelayNotification = idDelayNotification;
    }

    public String getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(String idTrain) {
        this.idTrain= idTrain;
    }

    public int getDelayDuration() {
        return delayDuration;
    }

    public void setDelayDuration(int delayDuration) {
        this.delayDuration = delayDuration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}