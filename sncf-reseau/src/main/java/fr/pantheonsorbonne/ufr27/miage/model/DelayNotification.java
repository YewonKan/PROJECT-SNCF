package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class DelayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrain", nullable = false)
    private Integer idTrain;

    @Column(name = "idTrajet", nullable = false)
    private Integer idTrajet;
    @Column(name = "delayDuration", nullable = false)
    private int delayDuration;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "creationTime", nullable = false)
    private Date creationTime;



    public DelayNotification(Integer idTrain, Integer idTrajet, int delayDuration, String reason, Date creationTime) {
        this.idTrain = idTrain;
        this.idTrajet = idTrajet;
        this.delayDuration = delayDuration;
        this.reason = reason;
        this.creationTime = creationTime;
    }

    public DelayNotification() {
    }

    public Integer getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Integer idTrain) {
        this.idTrain= idTrain;
    }

    public Integer getIdTrajet(){return idTrajet;}
    public void setIdTrajet(Integer idTrajet){this.idTrajet= idTrajet;}

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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}