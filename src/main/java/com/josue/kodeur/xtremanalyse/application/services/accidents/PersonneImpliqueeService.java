package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface PersonneImpliqueeService {
    PersonneImpliquee save(PersonneImpliquee personneImpliquee,
                           String typePersonne,
                           Long professionId,
                           String immatriculation,
                           Long accidentId) throws NotFoundException;

    PersonneImpliquee update(PersonneImpliquee personneImpliquee,
                           String typePersonne,
                           Long professionId,
                           String immatriculation,
                           Long accidentId) throws NotFoundException;
    PersonneImpliquee details(Long id) throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
    List<PersonneImpliquee> personneImpliqueeDansAccident(Long accidentID);
    List<PersonneImpliquee> listPersonnesImpliqueeDansVehicule(String immatriculation, Long accident);
}
