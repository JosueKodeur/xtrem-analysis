package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.lieux.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/region")
@AllArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/add")
    public Region addRegion(@RequestBody Region region){
        return regionService.save(region);
    }

    @PutMapping("/update/{id}")
    public Region updateRegion(@RequestBody Region region, @PathVariable("id") Long id) throws NotFoundException {
        return regionService.update(id, region);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRegion(@PathVariable("id") Long id) throws NotFoundException {
        regionService.delete(id);
    }

    @GetMapping("/prefectures/{id}")
    public List<PrefectureDto> prefecturesOfRegion(@PathVariable("id") Long id){
        return regionService.findPrefectures(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Region>> listRegions(){
        return new ResponseEntity<>(regionService.listAll(), HttpStatus.OK);
    }
}
