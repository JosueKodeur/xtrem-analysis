package com.josue.kodeur.xtremanalyse.services;

import com.josue.kodeur.xtremanalyse.api.dtos.QuartierDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;

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
    List<QuartierDto> findQuartiersOfVille(Long ID);
}
