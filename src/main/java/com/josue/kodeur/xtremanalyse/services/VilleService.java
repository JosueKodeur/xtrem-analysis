package com.josue.kodeur.xtremanalyse.services;

import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 *
 */

public interface VilleService {
    Ville save(Ville ville, Long prefectureID) throws NotFoundException;
    Ville update(Long ID, Ville ville) throws NotFoundException;
    void delete(Long ID);
    List<Ville> listAll();
}
