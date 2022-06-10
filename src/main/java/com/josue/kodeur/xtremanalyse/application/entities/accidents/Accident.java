package com.josue.kodeur.xtremanalyse.application.entities.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.ClassificationRoute;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_accident", nullable = false)
    private LocalDate dateAccident;

    @Column(name = "heure", nullable = false)
    private LocalDateTime heure;

    @Column(name = "zone", nullable = false)
    private String zone;

    @Column(name = "quartier", nullable = false, length = 70)
    private String quartier;

    @Column(name = "intersection_accident", nullable = false)
    private String intersectionAccident;

    @Column(name = "intersection", nullable = false, length = 150)
    private String pointRepere;

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Column(name = "route", nullable = false, length = 100)
    private String route;

    @Column(name = "circonstance_resume", nullable = false, length = 255)
    private String circonstanceResume;

    @Column(name = "type_jour", nullable = false)
    private String typeJour;

    @Column(name = "codification_causes", nullable = false, length = 255)
    private String codificationCauses;

    @Column(name = "lumiere", nullable = false, length = 120)
    private String lumiere;

    @Column(name = "profil_lieu", nullable = false, length = 40)
    private String profilLieu;

    @Column(name = "condition_atmospherique", nullable = false, length = 40)
    private String conditionAtmospherique;

    @Column(name = "is_marquage_sol", nullable = false, length = 5)
    private Boolean isMarquageSol;

    @Column(name = "trace_sol", nullable = false, length = 40)
    private String traceSol;

    @Column(name = "etat_chaussee", nullable = false, length = 120)
    private String etatChaussee;

    @Column(name = "controle_carrefour", nullable = false, length = 120)
    private String controleCarrefour;

    @Min(value = 0)
    @Column(name = "surface_atteinte", nullable = false, length = 40)
    private double surfaceAtteinte;

    @Min(value = 0)
    @Column(name = "nombre_morts", nullable = false)
    private int nombreMorts;

    @Min(value = 0)
    @Column(name = "nombre_vehicules_impliques", nullable = false)
    private int nombreVehiculesImpliques;

    @Min(value = 0)
    @Column(name = "nombre_panneaux_atteints", nullable = false)
    private int nombrePanneauxAtteints;

    @Min(value = 0)
    @Column(name = "nombre_balises_atteintes", nullable = false)
    private int nombreBalisesAtteintes;

    @Min(value = 0)
    @Column(name = "nombre_glissieres_atteintes", nullable = false)
    private int nombreGlissieresAtteintes;

    @Min(value = 0)
    @Column(name = "nombre_poteaux_atteints", nullable = false)
    private int nombrePoteauxAtteints;

    @Min(value = 0)
    @Column(name = "nombre_garde_fou_atteints", nullable = false)
    private int nombreGardeFouAtteints;

    @Min(value = 0)
    @Column(name = "nombre_ouvrage_beton_atteints", nullable = false)
    private int nombreOuvrageBetonAtteints;

    @Column(name = "autre_degats", nullable = false, length = 120)
    private String autreDegats;

    @Column(name = "carte_grise_image_recto", nullable = false)
    private String carteGriseImageRecto;

    @Column(name = "carte_grise_image_verso", nullable = false, length = 120)
    private String carteGriseImageVerso;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private Ville ville;

    @ManyToOne
    private TypeRoute typeRoute;

    @ManyToOne
    private ClassificationRoute classificationRoute;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

}