package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.lieux.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/ville")
@AllArgsConstructor
public class VilleController {

    private final VilleService villeService;

    @PostMapping("/add")
    public Ville addVille(@RequestBody Ville ville) throws NotFoundException {
        return villeService.save(ville);
    }

    @PutMapping("/update/{id}")
    public Ville updateVille(@RequestBody Ville ville, @PathVariable("id") Long id) throws NotFoundException {
        return villeService.update(id, ville);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVille(@PathVariable("id") Long id) throws NotFoundException {
        villeService.delete(id);
    }

    @GetMapping("/")
    public List<Ville> listVille(){
        return villeService.listAll();
    }
}
