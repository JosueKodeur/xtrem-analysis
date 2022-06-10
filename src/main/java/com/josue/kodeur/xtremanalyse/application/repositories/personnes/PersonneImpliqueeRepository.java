package com.josue.kodeur.xtremanalyse.application.repositories.personnes;

import com.josue.kodeur.xtremanalyse.application.dtos.projections.PersonneImpliqueeInfo;
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
public interface PersonneImpliqueeRepository extends JpaRepository<PersonneImpliquee, Long> {

    @Query("SELECT p FROM PersonneImpliquee p WHERE p.accident.id = :id")
    List<PersonneImpliqueeInfo> listPersonnesImpliqueeDansAccident(@Param("id") Long id);
}