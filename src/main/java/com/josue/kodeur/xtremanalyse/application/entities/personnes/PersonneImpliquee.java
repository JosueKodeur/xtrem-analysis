package com.josue.kodeur.xtremanalyse.application.entities.personnes;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "personnes_impliquees")
public class PersonneImpliquee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pieton_id")
    private Pieton pieton;

    @ManyToOne
    @JoinColumn(name = "conducteur_id")
    private Conducteur conducteur;

    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passager;

    @ManyToOne
    @JoinColumn(name = "accident_id")
    private Accident accident;

}