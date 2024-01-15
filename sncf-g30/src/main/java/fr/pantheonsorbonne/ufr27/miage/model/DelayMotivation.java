package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delay_motivation")
public class DelayMotivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMotivation", nullable = false)
    private Integer id;

    @Column(name = "delayMotivation")
    private String delayMotivation;

    @Column(name = "eligibility", nullable = false)
    private boolean eligibility;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDelayMotivation() {
        return delayMotivation;
    }

    public void setDelayMotivation(String delayMotivation) {
        this.delayMotivation = delayMotivation;
    }

    public boolean isEligibility() {
        return eligibility;
    }

    public void setEligibility(boolean eligibility) {
        this.eligibility = eligibility;
    }
}
