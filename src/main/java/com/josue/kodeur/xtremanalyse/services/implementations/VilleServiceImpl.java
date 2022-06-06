package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.dtos.QuartierDto;
import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Quartier;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.repositories.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.repositories.QuartierRepository;
import com.josue.kodeur.xtremanalyse.repositories.VilleRepository;
import com.josue.kodeur.xtremanalyse.services.VilleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {


    private final QuartierRepository quartierRepository;
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

    @Override
    public List<QuartierDto> findQuartiersOfVille(Long ID) {
        if (ID==null)
            return new ArrayList<>();
        List<Quartier> quartiers = quartierRepository.findQuartiersOfVille(ID);

        return quartiers.stream().map(mapperService::toQuartierDto).collect(Collectors.toList());
    }
}
