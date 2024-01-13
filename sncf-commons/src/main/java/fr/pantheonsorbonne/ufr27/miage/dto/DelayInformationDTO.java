package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement
public class DelayInformationDTO {
    private Integer idTrajet;
    private Integer idTrain;
    private Integer delayedMinutes;
    private String delayMotivation;
    private Date delayedDate;

    public DelayInformationDTO() {
    }

    public DelayInformationDTO(Integer idTrajet, Integer idTrain, Integer delayedMinutes, String delayMotivation, Date delayedDate) {
        this.idTrajet = idTrajet;
        this.idTrain = idTrain;
        this.delayedMinutes = delayedMinutes;
        this.delayMotivation = delayMotivation;
        this.delayedDate = delayedDate;
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
