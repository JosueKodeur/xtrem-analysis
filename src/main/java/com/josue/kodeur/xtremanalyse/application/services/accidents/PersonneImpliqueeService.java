package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

/**
 * @author JosueKodeur
 */

public interface PersonneImpliqueeService {
    PersonneImpliquee save(PersonneImpliquee personneImpliquee) throws NotFoundException;
    PersonneImpliquee details(Long id) throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
}
