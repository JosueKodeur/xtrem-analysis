package com.josue.kodeur.xtremanalyse.application.entities.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Conducteur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicules")
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "immatriculation", nullable = false, length = 20, unique = true)
    private String immatriculation;

    @Column(name = "pays_immatriculation", nullable = false, length = 20)
    private String paysImmatriculation;

    @Column(name = "genre", nullable = false, length = 20)
    private String genre;

    @Column(name = "is_premier_choc", nullable = false, length = 1)
    private Boolean isPremierChoc;

    @Column(name = "debut_circulation", nullable = false)
    private String debutCirculation;

    @Column(name = "is_controle_tech_valid", nullable = false, length = 1)
    private Boolean isControleTechValid;

    @Column(name = "is_assurance_valid", nullable = false, length = 1)
    private Boolean isAssuranceValid;

    @Column(name = "societe_assurance", nullable = false, length = 40)
    private String societeAssurance;

    @Column(name = "etat_general", nullable = false, length = 30)
    private String etatGeneral;

    @Column(name = "etat_pneus", nullable = false, length = 30)
    private String etatPneus;

    @Column(name = "etat_feux", nullable = false, length = 40)
    private String etatFeux;

    @Column(name = "type_chargement", nullable = false, length = 40)
    private String typeChargement;

    @Column(name = "volume_chargement", nullable = false, length = 40)
    private String volumeChargement;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "conducteur_id", insertable = false, updatable = false)
    private Conducteur conducteur;

}