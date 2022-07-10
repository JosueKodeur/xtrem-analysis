package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.PersonneImpliquee;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.PersonneImpliqueeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/personne-impliquee")
@AllArgsConstructor
@CrossOrigin("*")
public class PersonneImpliqueeController {

    private final PersonneImpliqueeService personneImpliqueeService;

    @GetMapping("/add")
    public PersonneImpliquee add(@RequestBody PersonneImpliquee personneImpliquee) throws NotFoundException {
        return personneImpliqueeService.save(personneImpliquee);
    }
}
