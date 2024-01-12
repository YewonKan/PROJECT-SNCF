package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient", nullable = false)
    private Integer id;

    @Column(name = "fname", nullable = false, length = 45)
    private String fname;

    @Column(name = "lname", nullable = false, length = 45)
    private String lname;

    @Column(name = "status", nullable = true, length = 45)
    private String status;

    @Column(name = "email", nullable = false)
    private String email;

    public Client(String fname, String lname, String email, String status) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.status = status;
    }

    public Client() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}