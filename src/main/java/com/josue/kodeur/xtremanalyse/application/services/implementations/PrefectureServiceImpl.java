package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Ville;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.RegionRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.VilleRepository;
import com.josue.kodeur.xtremanalyse.application.services.lieux.PrefectureService;
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
        currentPrefecture.setRegion(regionLocationRepository.findById(prefecture
                        .getRegion()
                        .getId())
                .orElseThrow(() ->new  NotFoundException("Region Introuvable")));
        currentPrefecture.setNom(prefecture.getNom());
        currentPrefecture.setUpdateAt(LocalDateTime.now());
        return currentPrefecture;
    }

    @Override
    public void delete(Long ID) throws NotFoundException{
        if (ID==null)
            throw new NotFoundException("Element introuvable");
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
