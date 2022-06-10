package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}