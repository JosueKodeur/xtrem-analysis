package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Long> {
}