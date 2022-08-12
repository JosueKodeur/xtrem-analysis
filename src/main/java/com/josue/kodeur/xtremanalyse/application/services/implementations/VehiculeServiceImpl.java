package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Assurance;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Vehicule;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AccidentRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AssuranceRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.VehiculeRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.VehiculeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AccidentRepository accidentRepository;
    private final AssuranceRepository assuranceRepository;

    @Override
    public Vehicule add(Vehicule vehicule, Long accidentID, Long assuranceId) throws NotFoundException {
        var assurance = assuranceRepository.findById(assuranceId).orElseThrow(() -> new NotFoundException("Element Introuvable"));
        Accident accident = accidentRepository.findById(accidentID).orElseThrow(() -> new NotFoundException("Accident Introuvable"));
        vehicule.setAccident(accident);
        vehicule.setAssurance(assurance);
        vehicule.setCreatedAt(LocalDateTime.now());
        vehicule.setUpdateAt(LocalDateTime.now());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule details(String immatriculation) throws NotFoundException {
        return vehiculeRepository.findByImmatriculation(immatriculation);
    }

    @Override
    public void delete(Long id) {
        var vehicule = vehiculeRepository.findById(id).orElseThrow();
        if(vehicule.getImmatriculation().equals("Aucun")){
            throw new RuntimeException("");
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> listAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Vehicule vehicule, Long accidentID, Long assuranceId) throws NotFoundException {
        var assurance = assuranceRepository.findById(assuranceId).orElseThrow(() -> new NotFoundException("Element Introuvable"));
        Vehicule oldVehicule = vehiculeRepository.findById(accidentID).orElseThrow(() -> new NotFoundException(""));
        oldVehicule.setUpdateAt(LocalDateTime.now());
        oldVehicule.setDebutCirculation(vehicule.getDebutCirculation());
        oldVehicule.setEtatFeux(vehicule.getEtatFeux());
        oldVehicule.setEtatGeneral(vehicule.getEtatGeneral());
        oldVehicule.setEtatPneus(vehicule.getEtatPneus());
        oldVehicule.setGenre(vehicule.getGenre());
        oldVehicule.setIsPremierChoc(vehicule.getIsPremierChoc());
        oldVehicule.setPaysImmatriculation(vehicule.getPaysImmatriculation());
        oldVehicule.setImmatriculation(vehicule.getImmatriculation());
        oldVehicule.setIsControleTechValid(vehicule.getIsControleTechValid());
        oldVehicule.setVolumeChargement(vehicule.getVolumeChargement());
        oldVehicule.setTypeChargement(vehicule.getTypeChargement());
        oldVehicule.setAssurance(assurance);
        return oldVehicule;
    }

    @Override
    public List<Vehicule> vehiculeForAccident(Long accidentID) throws NotFoundException{
        return vehiculeRepository.vehiculeByAccident(accidentID);
    }
}
