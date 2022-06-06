package com.josue.kodeur.xtremanalyse.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Setter @Getter @ToString
@Entity
@Table(name = "quartiers")
public class Quartier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

}