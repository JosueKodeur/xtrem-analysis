package com.josue.kodeur.xtremanalyse.services;

import com.josue.kodeur.xtremanalyse.api.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface PrefectureService {
    Prefecture save(Prefecture prefecture) throws NotFoundException;
    Prefecture update(Long ID, Prefecture prefecture) throws NotFoundException;
    void delete(Long ID);
    List<Prefecture> listAll();
    List<VilleDto> findVillesOfPrefecture(Long ID);
}
