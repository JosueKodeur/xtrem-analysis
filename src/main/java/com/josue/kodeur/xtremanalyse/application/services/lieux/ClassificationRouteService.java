package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.ClassificationRoute;

import java.util.List;

/**
 * @author GhostKodeur
 **/

public interface ClassificationRouteService {
    List<ClassificationRoute> listAll();
    ClassificationRoute add(ClassificationRoute classificationRoute);
}
