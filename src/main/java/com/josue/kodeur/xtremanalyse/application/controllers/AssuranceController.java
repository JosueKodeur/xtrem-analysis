package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Assurance;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AssuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/assurance")
@AllArgsConstructor
public class AssuranceController {

    private final AssuranceService assuranceService;

    @PostMapping("/add")
    public Assurance add(@RequestBody Assurance assurance){
        return assuranceService.add(assurance);
    }

    @PutMapping("/update")
    public Assurance update(@RequestBody Assurance assurance){
        return assuranceService.update(assurance);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        assuranceService.delete(id);
    }

    @GetMapping("/{id}")
    public Assurance details(@PathVariable("id") Long id){
        return assuranceService.details(id);
    }

    @GetMapping
    public List<Assurance> assuranceList(){
        return assuranceService.list();
    }

}
