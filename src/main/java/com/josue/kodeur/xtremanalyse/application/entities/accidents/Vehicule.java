package com.josue.kodeur.xtremanalyse.application.entities.accidents;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicules")
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "immatriculation", nullable = true, length = 20)
    private String immatriculation;

    @Column(name = "pays_immatriculation", nullable = true, length = 20)
    private String paysImmatriculation;

    @Column(name = "genre", nullable = true, length = 20)
    private String genre;

    @Column(name = "is_premier_choc", nullable = true, length = 1)
    private Boolean isPremierChoc;

    @Column(name = "debut_circulation", nullable = true)
    private String debutCirculation;

    @Column(name = "is_controle_tech_valid", nullable = true, length = 1)
    private Boolean isControleTechValid;

    @Column(name = "is_assurance_valid", nullable = true, length = 1)
    private Boolean isAssuranceValid;

    @Column(name = "etat_general", nullable = true, length = 30)
    private String etatGeneral;

    @Column(name = "etat_pneus", nullable = true, length = 30)
    private String etatPneus;

    @Column(name = "etat_feux", nullable = true, length = 40)
    private String etatFeux;

    @Column(name = "type_chargement", nullable = true, length = 40)
    private String typeChargement;

    @Column(name = "volume_chargement", nullable = true, length = 40)
    private String volumeChargement;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = true)
    private LocalDateTime updateAt;

    @ManyToOne
    private Accident accident;

    @ManyToOne
    private Assurance assurance;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonneImpliquee> personneImpliquees = new ArrayList<>();

}