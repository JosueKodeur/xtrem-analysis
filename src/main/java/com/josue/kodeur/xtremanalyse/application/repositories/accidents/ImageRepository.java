package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByNom(String nom);
}