package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Vehicule findByImmatriculation(String immatriculation);
    Vehicule findByAccident_IdAndImmatriculation(Long id, String immatriculation);
    @Query("SELECT v FROM Vehicule v WHERE v.accident.id = :id")
    List<Vehicule> vehiculeByAccident(@Param("id") Long accidentID);
    @Query("SELECT p FROM PersonneImpliquee p WHERE p.vehicule.accident.id= :id")
    List<PersonneImpliquee> listPersonnesImpliqueeDansAccident(@Param("id") Long id);

    @Query("SELECT v.personneImpliquees FROM Vehicule v WHERE v.accident.id=:accident and v.immatriculation=:immatriculation")
    List<PersonneImpliquee> listPersonnesImpliqueeDansVehicule(@Param("immatriculation") String immatriculation, @Param("accident") Long accident);
}