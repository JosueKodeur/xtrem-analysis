package com.josue.kodeur.xtremanalyse.application.repositories.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface TypeRouteRepository extends JpaRepository<TypeRoute, Long> {
}