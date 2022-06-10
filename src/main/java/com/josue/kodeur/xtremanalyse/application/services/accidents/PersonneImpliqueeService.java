package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Conducteur;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.Passager;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.Pieton;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

/**
 * @author JosueKodeur
 */

public interface PersonneImpliqueeService {
    PersonneImpliquee save(Conducteur conducteur, Pieton pieton, Passager passager) throws NotFoundException;
    PersonneImpliquee details() throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
}
