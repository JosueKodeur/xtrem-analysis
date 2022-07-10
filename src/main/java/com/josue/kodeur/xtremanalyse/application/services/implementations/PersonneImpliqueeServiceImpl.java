package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.Profession;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AccidentRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.PersonneImpliqueeRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.ProfessionRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.TypePersonneRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.PersonneImpliqueeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class PersonneImpliqueeServiceImpl implements PersonneImpliqueeService {
    private final PersonneImpliqueeRepository personneImpliqueeRepository;
    private final AccidentRepository accidentRepository;
    private final TypePersonneRepository typePersonneRepository;
    private final ProfessionRepository professionRepository;

    @Override
    public PersonneImpliquee save(PersonneImpliquee personneImpliquee) throws NotFoundException {
        Accident accident = accidentRepository.findById(personneImpliquee.getAccident().getId()).orElseThrow(() -> new NotFoundException("Accident Introuvable"));
        Profession profession = professionRepository.findById(personneImpliquee.getProfession().getId()).orElseThrow(() -> new NotFoundException("Profession Introuvable"));
        TypePersonne typePersonne = typePersonneRepository.findById(personneImpliquee.getTypePersonne().getId()).orElseThrow(() -> new NotFoundException("Type Personne Introuvable"));
        personneImpliquee.setCreatedAt(LocalDateTime.now());
        personneImpliquee.setUpdateAt(LocalDateTime.now());
        personneImpliquee.setAccident(accident);
        personneImpliquee.setTypePersonne(typePersonne);
        personneImpliquee.setProfession(profession);
        return personneImpliqueeRepository.save(personneImpliquee);
    }

    @Override
    public PersonneImpliquee details(Long id) throws NotFoundException {
        return personneImpliqueeRepository.findById(id).orElseThrow(() -> new NotFoundException("Personne Introuvable"));
    }

    @Override
    public void delete(Long ID) throws NotFoundException {
        personneImpliqueeRepository.deleteById(ID);
    }
}
