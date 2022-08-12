package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.PersonneImpliqueeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/personne-impliquee")
@AllArgsConstructor
@CrossOrigin("*")
public class PersonneImpliqueeController {

    private final PersonneImpliqueeService personneImpliqueeService;

    @PostMapping("/add/{typePersonne}/{professionId}/{immatriculation}/{accidentId}")
    public PersonneImpliquee add(@RequestBody PersonneImpliquee personneImpliquee,
                                 @PathVariable("typePersonne") String typePersonne,
                                 @PathVariable("professionId") Long professionId,
                                 @PathVariable("immatriculation") String immatriculation,
                                 @PathVariable("accidentId") Long accidentId) throws NotFoundException {
        return personneImpliqueeService.save(personneImpliquee, typePersonne, professionId, immatriculation, accidentId);
    }

    @PutMapping("/update/{typePersonne}/{professionId}/{immatriculation}/{accidentId}")
    public PersonneImpliquee update(@RequestBody PersonneImpliquee personneImpliquee,
                                 @PathVariable("typePersonne") String typePersonne,
                                 @PathVariable("professionId") Long professionId,
                                 @PathVariable("immatriculation") String immatriculation,
                                 @PathVariable("accidentId") Long accidentId) throws NotFoundException {
        return personneImpliqueeService.update(personneImpliquee, typePersonne, professionId, immatriculation, accidentId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        personneImpliqueeService.delete(id);
    }
    @GetMapping("/personne-dans-accident/{id}")
    public List<PersonneImpliquee> personneImpliqueeList(@PathVariable("id") Long accidentID){
        return personneImpliqueeService.personneImpliqueeDansAccident(accidentID);
    }

    @GetMapping("/personne-dans-vehicule/{id}/{id2}")
    public List<PersonneImpliquee> personneImpliqueeListByAccident(@PathVariable("id") String vehicule,
                                                                   @PathVariable("id2") Long accidentID){
        return personneImpliqueeService.listPersonnesImpliqueeDansVehicule(vehicule, accidentID);
    }
}
