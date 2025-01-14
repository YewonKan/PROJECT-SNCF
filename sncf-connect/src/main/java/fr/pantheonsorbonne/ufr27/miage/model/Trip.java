package fr.pantheonsorbonne.ufr27.miage.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrip", nullable = false)
    private Integer idTrip;

    @Column(name = "stationA")
    private String stationA;

    @Column(name = "stationD")
    private String stationD;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "idTrain")
    private Integer idTrain;

    @Column(name = "prix")
    private float prix;

    public Integer getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public String getStationA() {
        return stationA;
    }

    public void setStationA(String stationA) {
        this.stationA = stationA;
    }

    public String getStationD() {
        return stationD;
    }

    public void setStationD(String stationD) {
        this.stationD = stationD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Integer idTrain) {
        this.idTrain = idTrain;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}