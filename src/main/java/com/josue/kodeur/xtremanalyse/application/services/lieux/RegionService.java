package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

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
