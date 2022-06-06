package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.entities.lieux.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Long>{
    @Query("SELECT p FROM Prefecture p WHERE p.region.id = :id")
    List<Prefecture> findPrefecturesOfRegion(@Param("id") Long id);


}