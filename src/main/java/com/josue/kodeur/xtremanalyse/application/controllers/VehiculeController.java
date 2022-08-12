package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.VehiculeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/vehicule")
@CrossOrigin("*")
@AllArgsConstructor
public class VehiculeController {
    private final VehiculeService vehiculeService;

    @PostMapping("/add/{id}/{assuranceId}")
    public Vehicule add(@RequestBody Vehicule vehicule, @PathVariable("id") Long id, @PathVariable("assuranceId") Long assuranceID) throws NotFoundException {
        return vehiculeService.add(vehicule, id, assuranceID);
    }

    @PutMapping("/update/{id}/{assuranceId}")
    public Vehicule update(@RequestBody Vehicule vehicule, @PathVariable("id") Long id, @PathVariable("assuranceId") Long assuranceID) throws NotFoundException {
        return vehiculeService.update(vehicule, id, assuranceID);
    }

    @GetMapping("/all-by-accident/{id}")
    public List<Vehicule> vehiculeList(@PathVariable("id") Long id) throws NotFoundException {
        return vehiculeService.vehiculeForAccident(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        vehiculeService.delete(id);
    }

    @GetMapping("/list/")
    public List<Vehicule> list() {
        return vehiculeService.listAll();
    }
}
