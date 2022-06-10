package com.josue.kodeur.xtremanalyse.application.entities.personnes;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conducteurs")
public class Conducteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false, length = 40)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 40)
    private String prenom;

    @Min(value = 0)
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sexe", nullable = false, length = 1)
    private Character sexe;

    @Column(name = "nationalite", nullable = false, length = 30)
    private String nationalite;

    @Column(name = "lesion", nullable = false, length = 30)
    private String lesion;

    @Column(name = "protection", nullable = false)
    private String protection;

    @Column(name = "alcoolemie", nullable = false, length = 5)
    private Boolean alcoolemie;

    @Column(name = "stupefiant", nullable = false, length = 5)
    private Boolean stupefiant;

    @Column(name = "type_permis", nullable = false, length = 20)
    private String typePermis;

    @Column(name = "numero_permis", nullable = false, length = 40)
    private String numeroPermis;

    @Column(name = "annee_permis", nullable = false, length = 5)
    private String anneePermis;

    @Column(name = "pays_permis", nullable = false, length = 30)
    private String paysPermis;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @OneToOne(mappedBy = "conducteur", orphanRemoval = true)
    private Vehicule vehicule;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}