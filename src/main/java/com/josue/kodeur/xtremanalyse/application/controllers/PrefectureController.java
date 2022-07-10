package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.lieux.PrefectureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/prefectures/")
@AllArgsConstructor
public class PrefectureController {

    private final PrefectureService prefectureService;

    @PostMapping("/add")
    public Prefecture addPrefecture(@RequestBody Prefecture prefecture) throws NotFoundException {
        return prefectureService.save(prefecture);
    }

    @PutMapping("/update/{id}")
    public Prefecture updatePrefecture(@RequestBody Prefecture prefecture, @PathVariable("id") Long id) throws NotFoundException {
        if (id == null)
            return null;
        return prefectureService.update(id, prefecture);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePrefecture(@PathVariable("id") Long id) throws NotFoundException {
        prefectureService.delete(id);
    }

    @GetMapping("/villes/{id}")
    public List<VilleDto> villesOfPrefecture(@PathVariable("id") Long id){
        return prefectureService.findVillesOfPrefecture(id);
    }

    @GetMapping("/")
    public List<Prefecture> listPrefectures(){
        return prefectureService.listAll();
    }
}
