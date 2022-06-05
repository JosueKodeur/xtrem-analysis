package com.josue.kodeur.xtremanalyse.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity @Table(name = "prefectures")
public class Prefecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    @Column(nullable = false)
    private Long nombreHabitant;

    @OneToMany(mappedBy = "prefecture", orphanRemoval = true)
    private List<Ville> villes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}