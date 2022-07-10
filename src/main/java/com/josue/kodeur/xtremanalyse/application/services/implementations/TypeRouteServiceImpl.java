package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.TypeRoute;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.TypeRouteRepository;
import com.josue.kodeur.xtremanalyse.application.services.lieux.TypeRouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class TypeRouteServiceImpl implements TypeRouteService {

    private final TypeRouteRepository typeRouteRepository;

    @Override
    public List<TypeRoute> listAll() {
        return typeRouteRepository.findAll();
    }

    @Override
    public TypeRoute save(TypeRoute typeRoute) {
        if (typeRoute != null){
            return typeRouteRepository.save(typeRoute);
        }
        return null;
    }
}
