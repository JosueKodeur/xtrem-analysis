package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.dtos.projections.PersonneImpliqueeInfo;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Image;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AccidentRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.ImageRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.ClassificationRouteRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.TypeRouteRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.VilleRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.PersonneImpliqueeRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AccidentService;
import com.josue.kodeur.xtremanalyse.application.services.accidents.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JosueKodeur
 */

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final TypeRouteRepository typeRouteRepository;
    private final ClassificationRouteRepository classificationRouteRepository;
    private final PersonneImpliqueeRepository personneImpliqueeRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final VilleRepository villeRepository;

    @Override
    public Accident save(Accident accident, List<MultipartFile> files) throws NotFoundException, IOException {
        accident.setDateAccident(LocalDate.now());

        accident.setVille(villeRepository.findById(accident.getVille()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Ville introuvable")));

        accident.setTypeRoute(typeRouteRepository.findById(accident.getTypeRoute()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Type de Route introuvable")));
        accident.setClassificationRoute(classificationRouteRepository.findById(accident.getClassificationRoute()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Classification de route introuvable")));
        accident.setUpdateAt(LocalDateTime.now());
        accident.setCreatedAt(LocalDateTime.now());
        accidentRepository.save(accident);
        List<Image> images = new ArrayList<>();
        for (MultipartFile image: files) {
            if (image.getSize() > 0) {
                images.add((imageRepository.save(imageService.create(image))));
            }
        }
        accident.setImages(images);
        return accident;
    }

    @Override
    public void delete(Long ID) throws NotFoundException {
        if (ID==null) throw new NotFoundException("Element introuvable");
        accidentRepository.deleteById(ID);
    }

    @Override
    public Accident update(Long ID, Accident accident, List<MultipartFile> images) throws NotFoundException, IOException {
        Accident currentAccident = accidentRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Element introuvable"));
        currentAccident.setUpdateAt(LocalDateTime.now());

        currentAccident.setVille(villeRepository.findById(accident.getVille()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Ville introuvable")));

        currentAccident.setTypeRoute(typeRouteRepository.findById(accident.getTypeRoute()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Type de Route introuvable")));
        currentAccident.setClassificationRoute(classificationRouteRepository.findById(accident.getClassificationRoute()
                        .getId())
                .orElseThrow(() -> new NotFoundException("Classification de route introuvable")));
        currentAccident.setQuartier(accident.getQuartier());
        currentAccident.setDateAccident(accident.getDateAccident());
        currentAccident.setCarteGriseImageRecto(accident.getCarteGriseImageRecto());
        currentAccident.setCarteGriseImageVerso(accident.getCarteGriseImageVerso());
        currentAccident.setHeure(accident.getHeure());
        currentAccident.setZone(accident.getZone());
        currentAccident.setIntersectionAccident(accident.getIntersectionAccident());
        currentAccident.setPointRepere(accident.getPointRepere());
        currentAccident.setLatitude(accident.getLatitude());
        currentAccident.setLongitude(accident.getLongitude());
        currentAccident.setRoute(accident.getRoute());
        currentAccident.setCirconstanceResume(accident.getCirconstanceResume());
        currentAccident.setLumiere(accident.getLumiere());
        currentAccident.setProfilLieu(accident.getProfilLieu());
        currentAccident.setConditionAtmospherique(accident.getConditionAtmospherique());
        currentAccident.setCodificationCauses(accident.getCodificationCauses());
        currentAccident.setIsMarquageSol(accident.getIsMarquageSol());
        currentAccident.setTraceSol(accident.getTraceSol());
        currentAccident.setEtatChaussee(accident.getEtatChaussee());
        currentAccident.setControleCarrefour(accident.getControleCarrefour());
        currentAccident.setSurfaceAtteinte(accident.getSurfaceAtteinte());
        currentAccident.setNombreMorts(accident.getNombreMorts());
        currentAccident.setNombreVehiculesImpliques(accident.getNombreVehiculesImpliques());
        currentAccident.setNombrePanneauxAtteints(accident.getNombrePanneauxAtteints());
        currentAccident.setNombreBalisesAtteintes(accident.getNombreBalisesAtteintes());
        currentAccident.setNombrePoteauxAtteints(accident.getNombrePoteauxAtteints());
        currentAccident.setNombreGlissieresAtteintes(accident.getNombreGlissieresAtteintes());
        currentAccident.setNombreGardeFouAtteints(accident.getNombreGardeFouAtteints());
        currentAccident.setNombreOuvrageBetonAtteints(accident.getNombreOuvrageBetonAtteints());
        currentAccident.setAutreDegats(accident.getAutreDegats());

        List<Image> files = new ArrayList<>();

        for (MultipartFile image: images) {
            if (image.getSize() > 0) {
                Image image1 = imageRepository.findByNom(image.getOriginalFilename());
                if (!currentAccident.getImages().contains(image1)){
                    files.add(imageRepository.save(imageService.create(image)));
                }
            }
        }
        files.forEach(file -> currentAccident.getImages().add(file));
        return currentAccident;
    }

    @Override
    public List<Accident> listAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Accident details(Long ID) throws NotFoundException {
        return accidentRepository.findById(ID)
                .orElseThrow(()-> new NotFoundException("Element introuvable"));
    }

    @Override
    public List<PersonneImpliqueeInfo> listPersonnesImpliqueeDansAccident(Long ID) throws NotFoundException{
        if (ID == null)
            throw new NotFoundException("Element introuvable");
        return personneImpliqueeRepository.listPersonnesImpliqueeDansAccident(ID);
    }

}
