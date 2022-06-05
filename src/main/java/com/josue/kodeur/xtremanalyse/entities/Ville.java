package com.josue.kodeur.xtremanalyse.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "villes")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    @Column(nullable = false)
    private Long nombreHabitant;

    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quartier> quartiers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "prefecture_id")
    private Prefecture prefecture;

}