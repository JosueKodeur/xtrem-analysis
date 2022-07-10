package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;

import java.util.List;

/**
 * @author GhostKodeur
 **/

public interface TypeRouteService {
    List<TypeRoute> listAll();
    TypeRoute save(TypeRoute typeRoute);
}
