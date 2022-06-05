package com.josue.kodeur.xtremanalyse.controllers;

import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Quartier;
import com.josue.kodeur.xtremanalyse.entities.Region;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JosueKodeur
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/region")
public class RegionController{

    LocationService<Region> regionLocationService;
    LocationService<Prefecture> prefectureLocationService;
    LocationService<Ville> villeLocationService;
    LocationService<Quartier> quartierLocationService;

    @PostMapping("/add")
    public Region add(@RequestBody ){

    }


}
