package com.josue.kodeur.xtremanalyse.controllers;

import com.josue.kodeur.xtremanalyse.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Region;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.services.PrefectureService;
import com.josue.kodeur.xtremanalyse.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("/api/v1/prefecture")
@AllArgsConstructor
public class PrefectureController {
    private PrefectureService prefectureService;

    @PostMapping("/add")
    public Prefecture add(@RequestBody Prefecture prefecture) throws NotFoundException{
        return prefectureService.save(prefecture);
    }

    @PutMapping("/update/{id}")
    public Prefecture update(@RequestBody Prefecture prefecture, @PathVariable("id") Long id) throws NotFoundException {
        if (id == null)
            return null;
        return prefectureService.update(id, prefecture);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        prefectureService.delete(id);
    }

    @GetMapping("/villes/{id}")
    public List<VilleDto> villesOfPrefecture(@PathVariable("id") Long id){
        return prefectureService.findVillesOfPrefecture(id);
    }

    @GetMapping("/")
    public List<Prefecture> list(){
        return prefectureService.listAll();
    }
}
