package com.josue.kodeur.xtremanalyse.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString
@Entity
@Table(name = "quartiers")
public class Quartier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

}