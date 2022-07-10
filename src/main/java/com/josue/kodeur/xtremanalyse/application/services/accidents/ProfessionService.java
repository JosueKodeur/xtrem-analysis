package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Profession;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;

import java.util.List;

/**
 * @author GhostKodeur
 **/


public interface ProfessionService {
    Profession add(Profession profession);
    Profession update(Profession profession, Long id);
    List<Profession> listAll();
    void delete(Long id);
}
