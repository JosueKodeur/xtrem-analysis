package com.josue.kodeur.xtremanalyse.application.services.lieux;

import com.josue.kodeur.xtremanalyse.application.entities.lieux.ClassificationRoute;
import com.josue.kodeur.xtremanalyse.application.repositories.lieux.ClassificationRouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class ClassificationRouteServiceImpl implements ClassificationRouteService {
    private final ClassificationRouteRepository classificationRouteRepository;

    @Override
    public List<ClassificationRoute> listAll() {
        return classificationRouteRepository.findAll();
    }

    @Override
    public ClassificationRoute add(ClassificationRoute classificationRoute) {
        return classificationRouteRepository.save(classificationRoute);
    }
}
