package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    Assurance findByNom(String nom);

    @Query("SELECT a FROM Assurance a WHERE a.nom <> 'Pieton' ")
    List<Assurance> findAllAssurance();
}