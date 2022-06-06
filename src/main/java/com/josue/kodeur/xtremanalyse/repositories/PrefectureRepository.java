package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.dtos.RegionDto;
import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Long>{
    @Query("SELECT p FROM Prefecture p WHERE p.region.id = :id")
    List<Prefecture> findPrefecturesOfRegion(@Param("id") Long id);


}