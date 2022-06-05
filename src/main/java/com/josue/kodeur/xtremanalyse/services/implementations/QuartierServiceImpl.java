package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.entities.Quartier;
import com.josue.kodeur.xtremanalyse.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JosueKodeur
 */

@Service
public class QuartierServiceImpl implements LocationService<Quartier> {


    @Override
    public Quartier add(Quartier l) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Quartier update(Quartier quartier) {
        return null;
    }

    @Override
    public List<Quartier> listAll() {
        return null;
    }
}
