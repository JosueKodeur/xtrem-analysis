package com.josue.kodeur.xtremanalyse.services.implementations;

import com.josue.kodeur.xtremanalyse.entities.Quartier;
import com.josue.kodeur.xtremanalyse.entities.Ville;
import com.josue.kodeur.xtremanalyse.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.repositories.QuartierRepository;
import com.josue.kodeur.xtremanalyse.repositories.VilleRepository;
import com.josue.kodeur.xtremanalyse.services.QuartierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JosueKodeur
 */

@AllArgsConstructor
@Service
@Transactional
public class QuartierServiceImpl implements QuartierService {

    private final VilleRepository villeLocationRepository;
    private final QuartierRepository quartierLocationRepository;

    @Override
    public Quartier save(String nom, Long villeID) throws NotFoundException {
        Ville ville = villeLocationRepository.findById(villeID)
                .orElseThrow(() -> new NotFoundException("Ville Introuvable"));
        Quartier quartier = new Quartier(null, nom, ville);
        quartierLocationRepository.save(quartier);
        return quartier;
    }

    @Override
    public Quartier update(Long ID,Quartier quartier) throws NotFoundException{
        Quartier currentQuartier = quartierLocationRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Ville Introuvable"));
        currentQuartier.setName(quartier.getName());
        currentQuartier.setVille(quartier.getVille());
        return currentQuartier;
    }

    @Override
    public void delete(Long ID) {
        quartierLocationRepository.deleteById(ID);
    }

    @Override
    public List<Quartier> listAll() {
        return quartierLocationRepository.findAll();
    }
}
