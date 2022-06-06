package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.api.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.mappers.MapperService;
import com.josue.kodeur.xtremanalyse.repositories.PrefectureRepository;
import com.josue.kodeur.xtremanalyse.repositories.RegionRepository;
import com.josue.kodeur.xtremanalyse.services.RegionService;
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
        if (ID != null)
            regionRepository.deleteById(ID);
        throw new NotFoundException(errorMessage);
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
