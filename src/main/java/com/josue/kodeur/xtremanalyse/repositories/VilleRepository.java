package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.entities.lieux.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    @Query("SELECT v FROM Ville v WHERE v.prefecture.id = :id")
    List<Ville> findVillesOfPrefecture(@Param("id") Long id);
}