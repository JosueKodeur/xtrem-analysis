package com.josue.kodeur.xtremanalyse.application.entities.personnes;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "personnes_impliquees")
public class PersonneImpliquee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "cni", nullable = true, length = 40)
    private String cni;

    @Column(name = "nom", nullable = true, length = 40)
    private String nom;

    @Column(name = "prenom", nullable = true, length = 40)
    private String prenom;

    @Min(value = 0)
    @Column(name = "age", nullable = true)
    private int age;

    @Column(name = "sexe", nullable = true, length = 1)
    private String sexe;

    @Column(name = "nationalite", nullable = true, length = 30)
    private String nationalite;

    @Column(name = "lesion", nullable = true, length = 30)
    private String lesion;

    @Column(name = "protection", nullable = true)
    private String protection;

    @Column(name = "gravite", nullable = true, length = 40)
    private String gravite;

    @Column(name = "alcoolemie", nullable = true, length = 1)
    private Boolean alcoolemie;

    @Column(name = "stupefiant", nullable = true, length = 1)
    private Boolean stupefiant;

    @Column(name = "type_permis", nullable = true, length = 20)
    private String typePermis;

    @Column(name = "numero_permis", nullable = true, length = 40)
    private String numeroPermis;

    @Column(name = "annee_permis", nullable = true, length = 5)
    private String anneePermis;

    @Column(name = "pays_permis", nullable = true, length = 30)
    private String paysPermis;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;

    @ManyToOne
    private Profession profession;

    @ManyToOne
    private TypePersonne typePersonne;

    @ManyToOne
    private Vehicule vehicule;

}