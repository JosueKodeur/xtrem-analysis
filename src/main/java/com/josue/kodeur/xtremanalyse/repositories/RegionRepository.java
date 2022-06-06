package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.dtos.RegionDto;
import com.josue.kodeur.xtremanalyse.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author JosueKodeur
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
