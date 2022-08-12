package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.dtos.projections.PersonneImpliqueeInfo;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author JosueKodeur
 */


public interface AccidentService {
    Accident save(Accident accident, String userMatricule, Long classificationRouteId, Long typeRouteId, Long villeId, List<MultipartFile> images) throws NotFoundException, IOException;
    void delete(Long ID) throws NotFoundException;
    Accident update(Long ID, Accident accident, String userMatricule, Long classificationRouteId, Long typeRouteId, Long villeId,  List<MultipartFile> images) throws NotFoundException, IOException;
    List<Accident> listAll();
    Accident details(Long ID) throws NotFoundException;
}
