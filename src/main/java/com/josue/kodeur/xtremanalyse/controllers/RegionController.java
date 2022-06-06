package com.josue.kodeur.xtremanalyse.controllers;

import com.josue.kodeur.xtremanalyse.api.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JosueKodeur
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/region")
public class RegionController{

    private RegionService regionService;

    @PostMapping("/add")
    public Region add(@RequestBody Region region){
        return regionService.save(region);
    }

    @PutMapping("/update/{id}")
    public Region update(@RequestBody Region region, @PathVariable("id") Long id) throws NotFoundException {
        return regionService.update(id, region);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        regionService.delete(id);
    }

    @GetMapping("/prefectures/{id}")
    public List<PrefectureDto> prefecturesOfRegion(@PathVariable("id") Long id){
        return regionService.findPrefectures(id);
    }

    @GetMapping("/")
    public List<Region> list(){
        return regionService.listAll();
    }
}
