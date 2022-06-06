package com.josue.kodeur.xtremanalyse.services;

import com.josue.kodeur.xtremanalyse.api.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Region;
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
