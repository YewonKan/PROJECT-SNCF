
package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrain", nullable = false)
    private Integer idTrain;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quota", nullable = false)
    private Integer quota;

}
