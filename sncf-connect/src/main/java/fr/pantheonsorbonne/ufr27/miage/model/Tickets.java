package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;


@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTicket", nullable = false)
    private Integer idTicket;

    @JoinColumn(name = "idTrip", nullable = false)
    private Integer idTrip;

    @JoinColumn(name = "idCustomer", nullable = true)
    private Integer idCustomers;

    @JoinColumn(name = "fname", nullable = true)
    private String fname;
    @JoinColumn(name = "lname", nullable = true)
    private String lname;

    @JoinColumn(name = "email", nullable = true)
        private String email;

    @JoinColumn(name = "phone", nullable = true)
    private String phone;

    @JoinColumn(name = "prix", nullable = true)
    private Float prix;

    @JoinColumn(name = "idTrain", nullable = true)
    private Integer idTrain;

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Integer getIdTrip() {
        return idTrip;
    }
    
    public Integer getIdCustomers() {
        return idCustomers;
    }

    public void setIdCustomers(Integer idCustomers) {
        this.idCustomers = idCustomers;
    }

    public void setIdTrip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public Integer getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(Integer idTrain) {
        this.idTrain = idTrain;
    }
}



