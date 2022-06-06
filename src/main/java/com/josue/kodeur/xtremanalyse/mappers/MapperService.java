package com.josue.kodeur.xtremanalyse.mappers;

import com.josue.kodeur.xtremanalyse.api.dtos.PrefectureDto;
import com.josue.kodeur.xtremanalyse.api.dtos.QuartierDto;
import com.josue.kodeur.xtremanalyse.api.dtos.RegionDto;
import com.josue.kodeur.xtremanalyse.api.dtos.VilleDto;
import com.josue.kodeur.xtremanalyse.entities.lieux.Prefecture;
import com.josue.kodeur.xtremanalyse.entities.lieux.Quartier;
import com.josue.kodeur.xtremanalyse.entities.lieux.Region;
import com.josue.kodeur.xtremanalyse.entities.lieux.Ville;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author JosueKodeur
 */

@Service
public class MapperService {

    public Region toRegion(RegionDto dto){
        Region region = new Region();
        BeanUtils.copyProperties(dto, region);
        return region;
    }

    public RegionDto toRegionDto(Region region){
        RegionDto regionDto = new RegionDto();
        BeanUtils.copyProperties(region, regionDto);
        return regionDto;
    }

    public Prefecture toPrefecture(PrefectureDto dto){
        Prefecture prefecture = new Prefecture();
        BeanUtils.copyProperties(dto, prefecture);
        return prefecture;
    }

    public PrefectureDto toPrefectureDto(Prefecture prefecture){
        PrefectureDto prefectureDto = new PrefectureDto();
        BeanUtils.copyProperties(prefecture, prefectureDto);
        return prefectureDto;
    }

    public Ville toVille(VilleDto dto){
        Ville ville = new Ville();
        BeanUtils.copyProperties(dto, ville);
        return ville;
    }

    public VilleDto toVilleDto(Ville ville){
        VilleDto villeDto = new VilleDto();
        BeanUtils.copyProperties(ville, villeDto);
        return villeDto;
    }

    public Quartier toQuartier(QuartierDto dto){
        Quartier quartier = new Quartier();
        BeanUtils.copyProperties(dto, quartier);
        return quartier;
    }

    public QuartierDto toQuartierDto(Quartier quartier){
        QuartierDto quartierDto = new QuartierDto();
        BeanUtils.copyProperties(quartier, quartierDto);
        return quartierDto;
    }


}
