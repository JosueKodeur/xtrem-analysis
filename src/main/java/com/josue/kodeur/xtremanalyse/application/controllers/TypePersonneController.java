package com.josue.kodeur.xtremanalyse.application.controllers;


import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.TypePersonneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/type-personne")
@AllArgsConstructor
@CrossOrigin("*")
public class TypePersonneController {
    private final TypePersonneService typePersonneService;

    @PostMapping("/add")
    public TypePersonne add(@RequestBody TypePersonne typePersonne) {
        return typePersonneService.add(typePersonne);
    }

    @PutMapping("/update/{id}")
    public TypePersonne update(@RequestBody TypePersonne typePersonne, @PathVariable("id") Long id) throws NotFoundException {
        return typePersonneService.update(typePersonne, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        typePersonneService.delete(id);
    }

    @GetMapping("/")
    public List<TypePersonne> listAll(){
        return typePersonneService.listAll();
    }
}
