package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Image;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AccidentRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AssuranceRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.ImageRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.VehiculeRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.ClassificationRouteRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.TypeRouteRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.VilleRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AccidentService;
import com.josue.kodeur.xtremanalyse.application.services.accidents.ImageService;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.*;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final UserRepository userRepository;
    private final TypeRouteRepository typeRouteRepository;
    private final ClassificationRouteRepository classificationRouteRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final VilleRepository villeRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AssuranceRepository assuranceRepository;

    @Override
    public Accident save(Accident accident,
                         String userMatricule,
                         Long classificationRouteId,
                         Long typeRouteId,
                         Long villeId,List<MultipartFile> files) throws NotFoundException, IOException {
        accident.setDateAccident(LocalDate.now());
        accident.setUser(userRepository.findByUserMatricule(userMatricule));

        accident.setVille(villeRepository.findById(villeId)
                .orElseThrow(() -> new NotFoundException("Ville introuvable")));

        accident.setTypeRoute(typeRouteRepository.findById(typeRouteId)
                .orElseThrow(() -> new NotFoundException("Type de Route introuvable")));

        accident.setClassificationRoute(classificationRouteRepository.findById(classificationRouteId)
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
        var vehicule = new Vehicule();
        vehicule.setAssurance(assuranceRepository.findByNom("Pieton"));
        vehicule.setAccident(accident);
        vehicule.setCreatedAt(LocalDateTime.now());
        vehicule.setImmatriculation("Pieton");
        vehiculeRepository.save(vehicule);
        return accident;
    }

    @Override
    public void delete(Long ID) throws NotFoundException {
        Path imageFolder = Paths.get(System.getProperty(SERVER_FOLDER)+BASE_FOLDER).toAbsolutePath().normalize();
        if (ID==null) throw new NotFoundException("Element introuvable");
        var accident = accidentRepository.findById(ID).orElseThrow();
        accident.getImages().forEach(image -> {
            try {
                Files.deleteIfExists(Paths.get(imageFolder+"/"+ image.getNom()+APPLICATION_NAME +"."+IMAGE_EXTENSION));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        accidentRepository.deleteById(ID);
    }

    @Override
    public Accident update(Long ID, Accident accident,
                           String userMatricule,
                           Long classificationRouteId,
                           Long typeRouteId, Long villeId, List<MultipartFile> images) throws NotFoundException, IOException {
        Accident currentAccident = accidentRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Element introuvable"));

        currentAccident.setVille(villeRepository.findById(villeId)
                .orElseThrow(() -> new NotFoundException("Ville introuvable")));

        currentAccident.setTypeRoute(typeRouteRepository.findById(typeRouteId)
                .orElseThrow(() -> new NotFoundException("Type de Route introuvable")));
        currentAccident.setClassificationRoute(classificationRouteRepository.findById(classificationRouteId)
                .orElseThrow(() -> new NotFoundException("Classification de route introuvable")));
        currentAccident.setUpdateAt(LocalDateTime.now());
        currentAccident.setQuartier(accident.getQuartier());
        currentAccident.setDateAccident(accident.getDateAccident());
        currentAccident.setHeure(accident.getHeure());
        currentAccident.setZone(accident.getZone());
        currentAccident.setIntersectionAccident(accident.getIntersectionAccident());
        currentAccident.setPointRepere(accident.getPointRepere());
        currentAccident.setLatitude(accident.getLatitude());
        currentAccident.setLongitude(accident.getLongitude());
        currentAccident.setRoute(accident.getRoute());
        currentAccident.setTroncon(accident.getTroncon());
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


}
