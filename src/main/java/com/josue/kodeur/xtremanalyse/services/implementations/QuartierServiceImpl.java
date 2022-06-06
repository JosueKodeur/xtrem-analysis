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

import java.time.LocalDateTime;
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
    public Quartier save(Quartier quartier) throws NotFoundException {
        Ville ville = villeLocationRepository.findById(quartier.getVille().getId())
                .orElseThrow(() -> new NotFoundException("Ville Introuvable"));
        quartier.setCreatedAt(LocalDateTime.now());
        quartier.setUpdateAt(LocalDateTime.now());
        quartier.setVille(ville);
        quartierLocationRepository.save(quartier);
        return quartier;
    }

    @Override
    public Quartier update(Long ID,Quartier quartier) throws NotFoundException{
        Quartier currentQuartier = quartierLocationRepository.findById(ID)
                .orElseThrow(() -> new NotFoundException("Quartier Introuvable"));
        currentQuartier.setVille(villeLocationRepository.findById(quartier
                .getVille()
                .getId()).orElseThrow(() -> new NotFoundException("Ville Introuvable")));
        currentQuartier.setNom(quartier.getNom());
        currentQuartier.setUpdateAt(LocalDateTime.now());
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
