package com.josue.kodeur.xtremanalyse.repositories;

import com.josue.kodeur.xtremanalyse.entities.lieux.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
