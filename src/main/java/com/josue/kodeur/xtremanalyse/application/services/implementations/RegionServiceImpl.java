package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.RegionRepository;
import com.josue.kodeur.xtremanalyse.application.services.lieux.RegionService;
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
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;
    private PrefectureRepository prefectureRepository;
    private MapperService mapperService;

    private static final String errorMessage = "Region Introuvable";

    @Override
    public Region save(Region region) {
        region.setCreatedAt(LocalDateTime.now());
        region.setUpdateAt(LocalDateTime.now());
        return regionRepository.save(region);
    }

    @Override
    public Region update(Long ID, Region region) throws NotFoundException {
        Region currentRegion = regionRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException(errorMessage));
        currentRegion.setNom(region.getNom());
        currentRegion.setNombreHabitant(region.getNombreHabitant());
        currentRegion.setUpdateAt(LocalDateTime.now());
        return currentRegion;
    }

    @Override
    public void delete(Long ID) throws NotFoundException{
        regionRepository.deleteById(ID);
    }

    @Override
    public List<Region> listAll() {
        return regionRepository.findAll();
    }

    @Override
    public List<PrefectureDto> findPrefectures(Long ID){
        if (ID == null)
            return new ArrayList<>();
        List<Prefecture> prefectures = prefectureRepository.findPrefecturesOfRegion(ID);
        return prefectures.stream()
                .map(mapperService::toPrefectureDto)
                .collect(Collectors.toList());
    }
}
