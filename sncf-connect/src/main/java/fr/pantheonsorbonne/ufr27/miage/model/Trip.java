package fr.pantheonsorbonne.ufr27.miage.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrip", nullable = false)
    private int idTrip;


    @Column(name = "stationA")
    private String stationA;

    @Column(name = "stationD")
    private String stationD;

    @Column(name = "date", nullable = false)
    private int date;

    @Column(name = "quota")
    private int quota;

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getQuota() { return quota; }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}