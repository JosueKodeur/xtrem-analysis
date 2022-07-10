package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Profession;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/profession")
@AllArgsConstructor
@CrossOrigin("*")
public class ProfessionController {
    private final ProfessionService professionService;

    @PostMapping("/add")
    public Profession add(@RequestBody Profession profession) {
        return professionService.add(profession);
    }

    @PutMapping("/update/{id}")
    public Profession update(@RequestBody Profession profession, @PathVariable("id") Long id) throws NotFoundException {
        return professionService.update(profession, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        professionService.delete(id);
    }

    @GetMapping("/")
    public List<Profession> listAll(){
        return professionService.listAll();
    }
}
