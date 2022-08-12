package com.josue.kodeur.xtremanalyse.application.entities.accidents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.ClassificationRoute;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;
    @Column(name = "date_accident", nullable = true)
    private LocalDate dateAccident;
    @Column(name = "heure", nullable = true)
    private LocalDateTime heure;
    @Column(name = "zone", nullable = true)
    private String zone;
    @Column(name = "quartier", nullable = true, length = 70)
    private String quartier;
    @Column(name = "intersection_accident", nullable = true)
    private String intersectionAccident;
    @Column(name = "intersection", nullable = true, length = 150)
    private String pointRepere;
    @Column(name = "latitude", nullable = true)
    private Float latitude;
    @Column(name = "longitude", nullable = true)
    private Float longitude;
    @Column(name = "route", nullable = true, length = 100)
    private String route;
    @Column(name = "troncon", length = 150, nullable = true)
    private String troncon;
    @Column(name = "circonstance_resume", nullable = true, length = 255)
    private String circonstanceResume;
    @Column(name = "type_jour", nullable = true)
    private String typeJour;
    @Column(name = "codification_causes", nullable = true, length = 255)
    private String codificationCauses;
    @Column(name = "lumiere", nullable = true, length = 120)
    private String lumiere;
    @Column(name = "profil_lieu", nullable = true, length = 40)
    private String profilLieu;
    @Column(name = "condition_atmospherique", nullable = true, length = 40)
    private String conditionAtmospherique;
    @Column(name = "is_marquage_sol", nullable = true, length = 5)
    private Boolean isMarquageSol;
    @Column(name = "trace_sol", nullable = true, length = 40)
    private String traceSol;
    @Column(name = "etat_chaussee", nullable = true, length = 120)
    private String etatChaussee;
    @Column(name = "controle_carrefour", nullable = true, length = 120)
    private String controleCarrefour;
    @Min(value = 0)
    @Column(name = "surface_atteinte", nullable = true, length = 40)
    private Double surfaceAtteinte;
    @Min(value = 0)
    @Column(name = "nombre_morts", nullable = true)
    private Integer nombreMorts;
    @Min(value = 0)
    @Column(name = "nombre_vehicules_impliques", nullable = true)
    private Integer nombreVehiculesImpliques;
    @Min(value = 0)
    @Column(name = "nombre_panneaux_atteints", nullable = true)
    private Integer nombrePanneauxAtteints;
    @Min(value = 0)
    @Column(name = "nombre_balises_atteintes", nullable = true)
    private Integer nombreBalisesAtteintes;
    @Min(value = 0)
    @Column(name = "nombre_glissieres_atteintes", nullable = true)
    private Integer nombreGlissieresAtteintes;
    @Min(value = 0)
    @Column(name = "nombre_poteaux_atteints", nullable = true)
    private Integer nombrePoteauxAtteints;
    @Min(value = 0)
    @Column(name = "nombre_garde_fou_atteints", nullable = true)
    private Integer nombreGardeFouAtteints;
    @Min(value = 0)
    @Column(name = "nombre_ouvrage_beton_atteints", nullable = true)
    private Integer nombreOuvrageBetonAtteints;
    @Column(name = "autre_degats", nullable = true, length = 120)
    private String autreDegats;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
    @ManyToOne
    private Ville ville;
    @ManyToOne
    private TypeRoute typeRoute;
    @ManyToOne
    private ClassificationRoute classificationRoute;
    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "accident",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Vehicule> vehicules = new ArrayList<>();
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

}