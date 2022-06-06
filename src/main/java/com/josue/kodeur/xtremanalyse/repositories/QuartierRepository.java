package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Quartier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuartierRepository extends JpaRepository<Quartier, Long> {
    @Query("SELECT q FROM Quartier q WHERE q.ville.id = :id")
    List<Quartier> findQuartiersOfVille(@Param("id") Long id);
}