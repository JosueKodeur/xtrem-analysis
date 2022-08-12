package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

import java.util.List;

/**
 * @author GhostKodeur
 **/


public interface VehiculeService {

    Vehicule add(Vehicule vehicule, Long accidentID, Long assuranceId) throws NotFoundException;
    Vehicule details(String immatriculation) throws NotFoundException;

    void delete(Long id);
    List<Vehicule> listAll();
    Vehicule update(Vehicule vehicule, Long accidentID, Long assuranceId) throws NotFoundException;
    List<Vehicule> vehiculeForAccident(Long accidentID) throws NotFoundException;
}
