package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient", nullable = false)
    private Integer id;

    @Column(name = "Cname", nullable = false, length = 45)
    private String Cname;

    @Column(name = "Cstatus", nullable = false, length = 45)
    private String Cstatus;

    public Client(String Cname, String Cstatus) {
        this.Cname = Cname;
        this.Cstatus = Cstatus;
    }

    public Client() {
    }

    public String getName() {
        return Cname;
    }

    public void setName(String Cname) {
        this.Cname = Cname;
    }

    public String getStatus() {
        return Cstatus;
    }

    public void setStatus(String Cstatus) {
        this.Cstatus = Cstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
