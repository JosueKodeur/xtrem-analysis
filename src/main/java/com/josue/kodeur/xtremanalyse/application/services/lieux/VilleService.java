package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 *
 */

public interface VilleService {
    Ville save(Ville ville) throws NotFoundException;
    Ville update(Long ID, Ville ville) throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
    List<Ville> listAll();
}
