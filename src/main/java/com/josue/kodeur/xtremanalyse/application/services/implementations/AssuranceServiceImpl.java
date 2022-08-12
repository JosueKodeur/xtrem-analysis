package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Assurance;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AssuranceRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AssuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class AssuranceServiceImpl implements AssuranceService {
    private final AssuranceRepository assuranceRepository;
    @Override
    public Assurance add(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

    @Override
    public Assurance details(Long id) {
        return assuranceRepository.findById(id).orElseThrow();
    }

    @Override
    public Assurance update(Assurance assurance) {
        var oldAssurance = assuranceRepository.findById(assurance.getId()).orElseThrow();
        oldAssurance.setNom(assurance.getNom());
        return oldAssurance;
    }

    @Override
    public void delete(Long id) {
        if (id != null)
            assuranceRepository.deleteById(id);
    }

    @Override
    public List<Assurance> list() {
        return assuranceRepository.findAllAssurance();
    }
}
