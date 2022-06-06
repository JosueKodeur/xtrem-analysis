package com.josue.kodeur.xtremanalyse.controllers;

import com.josue.kodeur.xtremanalyse.entities.Quartier;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.services.QuartierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("api/v1/quartiers")
@AllArgsConstructor
public class QuartierController {
    private QuartierService quartierService;

    @PostMapping("/add")
    public Quartier add(@RequestBody Quartier quartier) throws NotFoundException {
        return quartierService.save(quartier);
    }

    @PutMapping("/update/{id}")
    public Quartier update(@RequestBody Quartier quartier, @PathVariable("id") Long id) throws NotFoundException {
        if (id == null)
            return null;
        return quartierService.update(id, quartier);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        quartierService.delete(id);
    }


    @GetMapping("/")
    public List<Quartier> list(){
        return quartierService.listAll();
    }
}
