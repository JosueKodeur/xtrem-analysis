package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.VilleRepository;
import com.josue.kodeur.xtremanalyse.application.services.lieux.VilleService;
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
    private final MapperService mapperService;

    @Override
    public Ville save(Ville ville) throws NotFoundException{
        Prefecture prefecture = prefectureLocationRepository.findById(ville.getPrefecture().getId())
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
        currentVille.setPrefecture(prefectureLocationRepository.findById(ville
                .getPrefecture()
                .getId()).orElseThrow(() -> new NotFoundException("Prefecture introuvable")));
        currentVille.setNom(ville.getNom());
        currentVille.setNombreHabitant(ville.getNombreHabitant());
        currentVille.setUpdateAt(LocalDateTime.now());
        return currentVille;
    }

    @Override
    public void delete(Long ID) throws NotFoundException{
        if (ID == null)
            throw new NotFoundException("Ville introuvable");
        villeLocationRepository.deleteById(ID);
    }

    @Override
    public List<Ville> listAll() {
        return villeLocationRepository.findAll();
    }
}
