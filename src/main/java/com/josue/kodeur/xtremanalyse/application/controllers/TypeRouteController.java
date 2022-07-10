package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;
import com.josue.kodeur.xtremanalyse.application.services.lieux.TypeRouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/type-route")
@AllArgsConstructor
@CrossOrigin("*")
public class TypeRouteController {
    private final TypeRouteService typeRouteService;

    @GetMapping("/")
    public List<TypeRoute> listAll(){
        return typeRouteService.listAll();
    }
}
