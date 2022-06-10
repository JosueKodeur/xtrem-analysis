package com.josue.kodeur.xtremanalyse.application.repositories.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.ClassificationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface ClassificationRouteRepository extends JpaRepository<ClassificationRoute, Long> {
}