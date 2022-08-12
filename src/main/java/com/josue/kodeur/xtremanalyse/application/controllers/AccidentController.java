package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AccidentService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.SERVER_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

/**
 * @author GhostKodeur
 */

@RestController
@RequestMapping("/api/v1/accident")
@AllArgsConstructor
@CrossOrigin("*")
public class AccidentController {
    private final AccidentService accidentService;
    public static final String DIRECTORY = System.getProperty(SERVER_FOLDER)+BASE_FOLDER;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/add")
    public ResponseEntity<Accident> add(
            @RequestParam("heure") String heure, @RequestParam("zone") String zone,
            @RequestParam("quartier") String quartier, @RequestParam("intersectionAccident") String intersectionAccident,
            @RequestParam("pointRepere") String pointRepere, @RequestParam("latitude") Float latitude,
            @RequestParam("longitude") Float longitude, @RequestParam("route") String route,
            @RequestParam("troncon") String troncon, @RequestParam("circonstance_resume") String circonstance_resume,
            @RequestParam("type_jour") String type_jour, @RequestParam("codification_causes") String codification_causes,
            @RequestParam("lumiere") String lumiere, @RequestParam("profil_lieu") String profil_lieu,
            @RequestParam("condition_atmospherique") String condition_atmospherique, @RequestParam("is_marquage_sol") String is_marquage_sol,
            @RequestParam("trace_sol") String trace_sol, @RequestParam("etat_chaussee") String etat_chaussee,
            @RequestParam("controle_carrefour") String controle_carrefour, @RequestParam("surface_atteinte") Double surface_atteinte,
            @RequestParam("nombre_morts") int nombre_morts, @RequestParam("nombre_vehicules_impliques") int nombre_vehicules_impliques,
            @RequestParam("nombre_panneaux_atteints") int nombre_panneaux_atteints, @RequestParam("nombre_balises_atteintes") int nombre_balises_atteintes,
            @RequestParam("nombre_glissieres_atteintes") int nombre_glissieres_atteintes, @RequestParam("nombre_poteaux_atteints") int nombre_poteaux_atteints,
            @RequestParam("nombre_garde_fou_atteints") int nombre_garde_fou_atteints, @RequestParam("nombre_ouvrage_beton_atteints") int nombre_ouvrage_beton_atteints,
            @RequestParam("autre_degats") String autre_degats, @RequestParam("villeId") Long villeId,
            @RequestParam("classificationRouteId") Long classificationRouteId, @RequestParam("typeRouteId") Long  typeRouteId,
            @RequestParam("userMatricule") String userMatricule,
            @RequestParam("images") List<MultipartFile> files) throws NotFoundException, IOException {
        Accident accident = new Accident();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        accident.setHeure(LocalDateTime.parse(heure, df));
        accident.setZone(zone);
        accident.setRoute(route);
        accident.setProfilLieu(profil_lieu);
        accident.setConditionAtmospherique(condition_atmospherique);
        accident.setIsMarquageSol(Boolean.parseBoolean(is_marquage_sol));
        accident.setControleCarrefour(controle_carrefour);
        accident.setTypeJour(type_jour);
        accident.setNombrePanneauxAtteints(nombre_panneaux_atteints);
        accident.setIntersectionAccident(intersectionAccident);
        accident.setPointRepere(pointRepere);
        accident.setQuartier(quartier);
        accident.setEtatChaussee(etat_chaussee);
        accident.setTroncon(troncon);
        accident.setAutreDegats(autre_degats);
        accident.setTraceSol(trace_sol);
        accident.setCirconstanceResume(circonstance_resume);
        accident.setSurfaceAtteinte(surface_atteinte);
        accident.setCodificationCauses(codification_causes);
        accident.setNombreBalisesAtteintes(nombre_balises_atteintes);
        accident.setNombreGlissieresAtteintes(nombre_glissieres_atteintes);
        accident.setNombreGardeFouAtteints(nombre_garde_fou_atteints);
        accident.setNombrePoteauxAtteints(nombre_poteaux_atteints);
        accident.setNombreMorts(nombre_morts);
        accident.setNombreOuvrageBetonAtteints(nombre_ouvrage_beton_atteints);
        accident.setNombreVehiculesImpliques(nombre_vehicules_impliques);
        accident.setLatitude(latitude);
        accident.setLongitude(longitude);
        accident.setLumiere(lumiere);
        accidentService.save(accident, userMatricule, classificationRouteId, typeRouteId, villeId, files);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("ID", accident.getId().toString());
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(accident);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/update/{id}")
    public Accident update(@RequestParam("heure") String heure, @RequestParam("zone") String zone,
                           @RequestParam("quartier") String quartier, @RequestParam("intersectionAccident") String intersectionAccident,
                           @RequestParam("pointRepere") String pointRepere, @RequestParam("latitude") Float latitude,
                           @RequestParam("longitude") Float longitude, @RequestParam("route") String route,
                           @RequestParam("troncon") String troncon, @RequestParam("circonstance_resume") String circonstance_resume,
                           @RequestParam("type_jour") String type_jour, @RequestParam("codification_causes") String codification_causes,
                           @RequestParam("lumiere") String lumiere, @RequestParam("profil_lieu") String profil_lieu,
                           @RequestParam("condition_atmospherique") String condition_atmospherique, @RequestParam("is_marquage_sol") String is_marquage_sol,
                           @RequestParam("trace_sol") String trace_sol, @RequestParam("etat_chaussee") String etat_chaussee,
                           @RequestParam("controle_carrefour") String controle_carrefour, @RequestParam("surface_atteinte") Double surface_atteinte,
                           @RequestParam("nombre_morts") int nombre_morts, @RequestParam("nombre_vehicules_impliques") int nombre_vehicules_impliques,
                           @RequestParam("nombre_panneaux_atteints") int nombre_panneaux_atteints, @RequestParam("nombre_balises_atteintes") int nombre_balises_atteintes,
                           @RequestParam("nombre_glissieres_atteintes") int nombre_glissieres_atteintes, @RequestParam("nombre_poteaux_atteints") int nombre_poteaux_atteints,
                           @RequestParam("nombre_garde_fou_atteints") int nombre_garde_fou_atteints, @RequestParam("nombre_ouvrage_beton_atteints") int nombre_ouvrage_beton_atteints,
                           @RequestParam("autre_degats") String autre_degats, @RequestParam("villeId") Long villeId,
                           @RequestParam("classificationRouteId") Long classificationRouteId, @RequestParam("typeRouteId") Long  typeRouteId,
                           @RequestParam("userMatricule") String userMatricule,
                           @RequestParam("images") List<MultipartFile> files,
                           @PathVariable("id") Long id) throws NotFoundException, IOException {
        if (files == null)
            throw new RuntimeException("");
        Accident accident = new Accident();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        accident.setHeure(LocalDateTime.parse(heure, df));
        accident.setZone(zone);
        accident.setRoute(route);
        accident.setProfilLieu(profil_lieu);
        accident.setConditionAtmospherique(condition_atmospherique);
        accident.setIsMarquageSol(Boolean.parseBoolean(is_marquage_sol));
        accident.setControleCarrefour(controle_carrefour);
        accident.setTypeJour(type_jour);
        accident.setNombrePanneauxAtteints(nombre_panneaux_atteints);
        accident.setIntersectionAccident(intersectionAccident);
        accident.setPointRepere(pointRepere);
        accident.setQuartier(quartier);
        accident.setEtatChaussee(etat_chaussee);
        accident.setTroncon(troncon);
        accident.setAutreDegats(autre_degats);
        accident.setTraceSol(trace_sol);
        accident.setCirconstanceResume(circonstance_resume);
        accident.setSurfaceAtteinte(surface_atteinte);
        accident.setCodificationCauses(codification_causes);
        accident.setNombreBalisesAtteintes(nombre_balises_atteintes);
        accident.setNombreGlissieresAtteintes(nombre_glissieres_atteintes);
        accident.setNombreGardeFouAtteints(nombre_garde_fou_atteints);
        accident.setNombrePoteauxAtteints(nombre_poteaux_atteints);
        accident.setNombreMorts(nombre_morts);
        accident.setNombreOuvrageBetonAtteints(nombre_ouvrage_beton_atteints);
        accident.setNombreVehiculesImpliques(nombre_vehicules_impliques);
        accident.setLatitude(latitude);
        accident.setLongitude(longitude);
        accident.setLumiere(lumiere);
        return accidentService.update(id, accident, userMatricule, classificationRouteId, typeRouteId, villeId, files);
    }

    @GetMapping("/{id}")
    public Accident details(@PathVariable("id") Long id) throws NotFoundException{
        return accidentService.details(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundException{
        accidentService.delete(id);
    }

    @GetMapping("/")
    public List<Accident> listAll(){
        return accidentService.listAll();
    }

    @GetMapping(path = "/images/{filename}", produces = {IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] get(@PathVariable("filename")String filename) throws IOException{
        FileInputStream inputStream = new FileInputStream(DIRECTORY+filename);
        return IOUtils.toByteArray(inputStream);
//        return Files.readAllBytes(Paths.get(DIRECTORY+filename));
    }

}
