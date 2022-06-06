package com.josue.kodeur.xtremanalyse.controllers;

import com.josue.kodeur.xtremanalyse.dtos.QuartierDto;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("/api/v1/ville")
@AllArgsConstructor
public class VilleController {
    private VilleService villeService;

    @PostMapping("/add")
    public Ville add(@RequestBody Ville ville) throws NotFoundException{
        return villeService.save(ville);
    }

    @PutMapping("/update/{id}")
    public Ville update(@RequestBody Ville ville, @PathVariable("id") Long id) throws NotFoundException {
        return villeService.update(id, ville);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        villeService.delete(id);
    }

    @GetMapping("/quartiers/{id}")
    public List<QuartierDto> quartiersOfVille(@PathVariable("id") Long id){
        return villeService.findQuartiersOfVille(id);
    }

    @GetMapping("/")
    public List<Ville> list(){
        return villeService.listAll();
    }
}
