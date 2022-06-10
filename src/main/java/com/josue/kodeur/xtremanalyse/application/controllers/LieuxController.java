package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.application.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.lieux.PrefectureService;
import com.josue.kodeur.xtremanalyse.application.services.lieux.RegionService;
import com.josue.kodeur.xtremanalyse.application.services.lieux.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("/api/v1/locations/")
@AllArgsConstructor
public class LieuxController {

    private final RegionService regionService;
    private final PrefectureService prefectureService;
    private final VilleService villeService;


    @PostMapping("/region/add")
    public Region addRegion(@RequestBody Region region){
        return regionService.save(region);
    }

    @PutMapping("/region/update/{id}")
    public Region updateRegion(@RequestBody Region region, @PathVariable("id") Long id) throws NotFoundException {
        return regionService.update(id, region);
    }

    @DeleteMapping("/region/delete/{id}")
    public void deleteRegion(@PathVariable("id") Long id) throws NotFoundException {
        regionService.delete(id);
    }

    @GetMapping("/region/prefectures/{id}")
    public List<PrefectureDto> prefecturesOfRegion(@PathVariable("id") Long id){
        return regionService.findPrefectures(id);
    }

    @GetMapping("/region/")
    public List<Region> listRegions(){
        return regionService.listAll();
    }






    @PostMapping("/prefecture/add")
    public Prefecture addPrefecture(@RequestBody Prefecture prefecture) throws NotFoundException{
        return prefectureService.save(prefecture);
    }

    @PutMapping("/prefecture/update/{id}")
    public Prefecture updatePrefecture(@RequestBody Prefecture prefecture, @PathVariable("id") Long id) throws NotFoundException {
        if (id == null)
            return null;
        return prefectureService.update(id, prefecture);
    }

    @DeleteMapping("/prefecture/delete/{id}")
    public void deletePrefecture(@PathVariable("id") Long id) throws NotFoundException {
        prefectureService.delete(id);
    }

    @GetMapping("/prefecture/villes/{id}")
    public List<VilleDto> villesOfPrefecture(@PathVariable("id") Long id){
        return prefectureService.findVillesOfPrefecture(id);
    }

    @GetMapping("/prefecture/")
    public List<Prefecture> listPrefectures(){
        return prefectureService.listAll();
    }







    @PostMapping("/ville/add")
    public Ville addVille(@RequestBody Ville ville) throws NotFoundException{
        return villeService.save(ville);
    }

    @PutMapping("/ville/update/{id}")
    public Ville updateVille(@RequestBody Ville ville, @PathVariable("id") Long id) throws NotFoundException {
        return villeService.update(id, ville);
    }

    @DeleteMapping("/ville/delete/{id}")
    public void deleteVille(@PathVariable("id") Long id) throws NotFoundException {
        villeService.delete(id);
    }

    @GetMapping("/ville/")
    public List<Ville> listVille(){
        return villeService.listAll();
    }

}
