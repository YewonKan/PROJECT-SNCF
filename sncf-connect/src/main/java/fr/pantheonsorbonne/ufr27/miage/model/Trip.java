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

    @Column
    @JoinColumn(name = "stationD")
    private String stationD;

    @Column(name = "date", nullable = false)
    private Integer date;

    @Column(name = "quota")
    private Integer quota;

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

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getQuota() { return quota; }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }
}