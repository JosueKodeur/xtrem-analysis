package com.josue.kodeur.xtremanalyse.services;

import com.josue.kodeur.xtremanalyse.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.dtos.RegionDto;
import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.Region;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface RegionService {
    Region save(Region region);
    Region update(Long ID, Region region) throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
    List<Region> listAll();
    List<PrefectureDto> findPrefectures(Long ID);
}
