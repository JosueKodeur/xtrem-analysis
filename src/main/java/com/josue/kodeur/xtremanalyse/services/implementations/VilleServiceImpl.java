package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.repositories.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.repositories.VilleRepository;
import com.josue.kodeur.xtremanalyse.services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {

    private final PrefectureRepository prefectureLocationRepository;
    private final VilleRepository villeLocationRepository;

    @Override
    public Ville save(Ville ville, Long prefectureID) throws NotFoundException{
        Prefecture prefecture = prefectureLocationRepository.findById(prefectureID)
                .orElseThrow(() -> new NotFoundException("Prefecture Introuvable"));
        ville.setPrefecture(prefecture);
        ville.setCreatedAt(LocalDateTime.now());
        ville.setUpdateAt(LocalDateTime.now());
        villeLocationRepository.save(ville);
        return ville;
    }

    @Override
    public Ville update(Long ID, Ville ville) throws NotFoundException {
        Ville currentVille = villeLocationRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Ville Introuvable"));
        currentVille.setNom(ville.getNom());
        currentVille.setNombreHabitant(ville.getNombreHabitant());
        currentVille.setPrefecture(ville.getPrefecture());
        currentVille.setUpdateAt(LocalDateTime.now());
        return currentVille;
    }

    @Override
    public void delete(Long ID) {
        villeLocationRepository.deleteById(ID);
    }

    @Override
    public List<Ville> listAll() {
        return villeLocationRepository.findAll();
    }
}
