package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;

import java.util.List;

/**
 * @author GhostKodeur
 **/


public interface TypePersonneService {
    TypePersonne add(TypePersonne typePersonne);
    TypePersonne update(TypePersonne typePersonne, Long id);
    List<TypePersonne> listAll();
    void delete(Long id);
}
