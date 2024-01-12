package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;


@NamedQueries({ @NamedQuery(name = "getDelayedTimeForTicketId", query = "SELECT d FROM DelayInformation d WHERE d.idTicket = ?1") })
@Entity
public class Client {

    @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idClient", nullable = false)
        private Integer idClient;

        @Column(name = "typeFidelity", nullable = false, length = 50)
        private String typeFidelity;


    public Client( Integer idClient, String typeFidelity) {
        this.idClient = idClient;
        this.typeFidelity = typeFidelity;
    }

    public Client() {
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public void setTypeFidelity(String typeFidelity) {
        this.typeFidelity = typeFidelity;
    }

    public String getTypeFidelity() {
        return typeFidelity;
    }
}
