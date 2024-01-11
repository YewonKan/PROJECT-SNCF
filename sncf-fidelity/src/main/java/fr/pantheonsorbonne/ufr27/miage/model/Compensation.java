package fr.pantheonsorbonne.ufr27.miage.model;


import jakarta.persistence.*;



@Entity
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "type")
    private String type;

    @Column(name = "details")
    private String details;

    @Column(name = "validity_date")
    private String validityDate;

    public Compensation(Integer id, Client client, String type, String details, String validityDate) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.details = details;
        this.validityDate = validityDate;
    }

    public Compensation() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }
}



