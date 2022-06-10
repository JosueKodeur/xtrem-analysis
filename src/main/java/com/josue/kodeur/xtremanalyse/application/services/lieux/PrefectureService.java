package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.application.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface PrefectureService {
    Prefecture save(Prefecture prefecture) throws NotFoundException;
    Prefecture update(Long ID, Prefecture prefecture) throws NotFoundException;
    void delete(Long ID) throws NotFoundException;
    List<Prefecture> listAll();
    List<VilleDto> findVillesOfPrefecture(Long ID);
}
