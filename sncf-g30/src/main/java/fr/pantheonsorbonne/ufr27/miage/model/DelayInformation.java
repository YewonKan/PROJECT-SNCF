package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "getDelayedTimeForTrainAndTrajet",
                query = "SELECT d FROM delay_information d WHERE d.idTrain = ?1 AND d.idTrajet = ?2"
        )
})
@Entity
public class DelayInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification", nullable = false)
    private Integer id;

    @Column(name = "idTrajet", nullable = false)
    private Integer idTrajet;

    @Column(name = "idTrain", nullable = false)
    private Integer idTrain;

    @Column(name = "delayedMinutes", nullable = false)
    private Integer delayedMinutes;

    @Column(name = "delayMotivation", nullable = false, length = 100)
    private String delayMotivation;

    @Column(name = "delayedDate", nullable = false)
    private Date delayedDate;

    public DelayInformation(Integer idTrajet, Integer idTrain, Integer delayedMinutes, String delayMotivation, Date delayedDate) {
        this.idTrajet = idTrajet;
        this.idTrain = idTrain;
        this.delayedMinutes = delayedMinutes;
        this.delayMotivation = delayMotivation;
        this.delayedDate = delayedDate;
    }

    public DelayInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Integer idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Integer getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Integer idTrain) {
        this.idTrain = idTrain;
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

    public Date getDelayedDate() {
        return delayedDate;
    }

    public void setDelayedDate(Date delayedDate) {
        this.delayedDate = delayedDate;
    }
}
