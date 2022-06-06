package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Region;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.repositories.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.repositories.QuartierRepository;
import com.josue.kodeur.xtremanalyse.repositories.RegionRepository;
import com.josue.kodeur.xtremanalyse.repositories.VilleRepository;
import com.josue.kodeur.xtremanalyse.services.PrefectureService;
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

@AllArgsConstructor
@Service
@Transactional
public class PrefectureServiceImpl implements PrefectureService {

    private final VilleRepository villeRepository;
    private final PrefectureRepository prefectureRepository;
    private final RegionRepository regionLocationRepository;
    private final MapperService mapperService;

    @Override
    public Prefecture save(Prefecture prefecture) throws NotFoundException {
        Region region = regionLocationRepository.findById(prefecture.getRegion().getId())
                .orElseThrow(() -> new NotFoundException("Region Introuvable"));
        prefecture.setRegion(region);
        prefecture.setCreatedAt(LocalDateTime.now());
        prefecture.setUpdateAt(LocalDateTime.now());
        return prefectureRepository.save(prefecture);
    }

    @Override
    public Prefecture update(Long ID, Prefecture prefecture) throws NotFoundException {
        Prefecture currentPrefecture = prefectureRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Prefecture Introuvable"));
        currentPrefecture.setNom(prefecture.getNom());
        currentPrefecture.setNombreHabitant(prefecture.getNombreHabitant());
        currentPrefecture.setUpdateAt(LocalDateTime.now());
        currentPrefecture.setRegion(prefecture.getRegion());
        return currentPrefecture;
    }

    @Override
    public void delete(Long ID) {
        prefectureRepository.deleteById(ID);
    }

    @Override
    public List<VilleDto> findVillesOfPrefecture(Long ID){
        if (ID == null)
            return new ArrayList<>();
        List<Ville> villes = villeRepository.findVillesOfPrefecture(ID);
        return villes.stream().map(mapperService::toVilleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prefecture> listAll() {
        return prefectureRepository.findAll();
    }
}
