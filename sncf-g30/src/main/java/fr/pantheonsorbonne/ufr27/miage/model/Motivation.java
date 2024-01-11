package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

//@NamedQueries({
//        @NamedQuery(
//                name = "isEligibleMotivation",
//                query = "SELECT d  FROM delayMotivation d WHERE d.motivationText = ?1 "
//        )
//})
@Entity
public class Motivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motivationId", nullable = false)
    private Integer motivationId;

    @Column(name = "motivationText", nullable = false)
    private String motivationText;

    @Column(name = "motivationStatus", nullable = false)
    private boolean motivationStatus;

    public Motivation(String motivationText, boolean motivationStatus) {
        this.motivationText = motivationText;
        this.motivationStatus = motivationStatus;
    }

    public Motivation() {
    }

    public Integer getMotivationId() {
        return motivationId;
    }

    public void setMotivationId(Integer motivationId) {
        this.motivationId = motivationId;
    }

    public String getMotivationText() {
        return motivationText;
    }

    public void setMotivationText(String motivationText) {
        this.motivationText = motivationText;
    }

    public boolean getMotivationStatus() {
        return motivationStatus;
    }

    public void setMotivationStatus(boolean motivationStatus) {
        this.motivationStatus = motivationStatus;
    }
}
