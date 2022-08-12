package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.VehiculeRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.PersonneImpliqueeRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.ProfessionRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.TypePersonneRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.PersonneImpliqueeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class PersonneImpliqueeServiceImpl implements PersonneImpliqueeService {
    private final PersonneImpliqueeRepository personneImpliqueeRepository;
    private final VehiculeRepository vehiculeRepository;
    private final TypePersonneRepository typePersonneRepository;
    private final ProfessionRepository professionRepository;

    @Override
    public PersonneImpliquee save(PersonneImpliquee personneImpliquee,
                                  String typePersonne,
                                  Long professionId,
                                  String immatriculation,
                                  Long accidentId) throws NotFoundException {
        var vehicule = vehiculeRepository.findByAccident_IdAndImmatriculation(accidentId,immatriculation);
        var profession = professionRepository.findById(professionId).orElseThrow(() -> new NotFoundException("Profession Introuvable"));
        TypePersonne _typePersonne = typePersonneRepository.findByNom(typePersonne);
        if (_typePersonne == null || vehicule == null) {
            throw new NotFoundException("");
        }

        if (typePersonne.equals("Conducteur")){
            List<PersonneImpliquee> conducteurs = personneImpliqueeRepository.conducteurVehicule(immatriculation);
            if (conducteurs.isEmpty()){
                personneImpliquee.setTypePersonne(_typePersonne);
                personneImpliquee.setVehicule(vehicule);
                personneImpliquee.setCreatedAt(LocalDateTime.now());
                personneImpliquee.setUpdateAt(LocalDateTime.now());
                personneImpliquee.setProfession(profession);
            }
            else {
                return null;
            }
        }
        if (typePersonne.equals("Pieton")){
		var _vehicule = vehiculeRepository.findByAccident_IdAndImmatriculation(accidentId, "Pieton");
            personneImpliquee.setTypePersonne(_typePersonne);
            personneImpliquee.setVehicule(_vehicule);
            personneImpliquee.setCreatedAt(LocalDateTime.now());
            personneImpliquee.setUpdateAt(LocalDateTime.now());
            personneImpliquee.setProfession(profession);
        }
        if (typePersonne.equals("Passager")){
            personneImpliquee.setTypePersonne(_typePersonne);
            personneImpliquee.setVehicule(vehicule);
            personneImpliquee.setCreatedAt(LocalDateTime.now());
            personneImpliquee.setUpdateAt(LocalDateTime.now());
            personneImpliquee.setProfession(profession);
        }
        return personneImpliqueeRepository.save(personneImpliquee);
    }

    @Override
    public PersonneImpliquee update(PersonneImpliquee personneImpliquee, String typePersonne, Long professionId, String immatriculation, Long accidentId) throws NotFoundException {
        var personne = personneImpliqueeRepository.findById(personneImpliquee.getId()).orElseThrow();
        var vehicule = vehiculeRepository.findByAccident_IdAndImmatriculation(accidentId,immatriculation);
        var profession = professionRepository.findById(professionId).orElseThrow(() -> new NotFoundException("Profession Introuvable"));
        TypePersonne _typePersonne = typePersonneRepository.findByNom(typePersonne);
        if (_typePersonne == null || vehicule == null) {
            throw new NotFoundException("");
        }

        if (typePersonne.equals("Conducteur")){
            List<PersonneImpliquee> conducteurs = personneImpliqueeRepository.conducteurVehicule(immatriculation);
            if (conducteurs.isEmpty()){
                BeanUtils.copyProperties(personneImpliquee, personne);
                personne.setTypePersonne(_typePersonne);
                personne.setVehicule(vehicule);
                personne.setUpdateAt(LocalDateTime.now());
                personne.setProfession(profession);
            }
            else {
                return null;
            }
        }
        if (typePersonne.equals("Pieton")){
            var _vehicule = vehiculeRepository.findByAccident_IdAndImmatriculation(accidentId, "Pieton");
            BeanUtils.copyProperties(personneImpliquee, personne);
            personne.setTypePersonne(_typePersonne);
            personne.setVehicule(_vehicule);
            personne.setUpdateAt(LocalDateTime.now());
            personne.setProfession(profession);
        }
        if (typePersonne.equals("Passager")){
            BeanUtils.copyProperties(personneImpliquee, personne);
            personne.setTypePersonne(_typePersonne);
            personne.setVehicule(vehicule);
            personne.setUpdateAt(LocalDateTime.now());
            personne.setProfession(profession);
        }
        return personne;
    }

    @Override
    public PersonneImpliquee details(Long id) throws NotFoundException {
        return personneImpliqueeRepository.findById(id).orElseThrow(() -> new NotFoundException("Personne Introuvable"));
    }

    @Override
    public void delete(Long ID) throws NotFoundException {
        personneImpliqueeRepository.deleteById(ID);
    }

    @Override
    public List<PersonneImpliquee> personneImpliqueeDansAccident(Long accidentID) {
        return vehiculeRepository.listPersonnesImpliqueeDansAccident(accidentID);
    }

    @Override
    public List<PersonneImpliquee> listPersonnesImpliqueeDansVehicule(String immatriculation, Long accident) {
        return vehiculeRepository.listPersonnesImpliqueeDansVehicule(immatriculation, accident);
    }
}
