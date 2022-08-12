package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Assurance;
import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AssuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author GhostKodeur
 **/

public interface AssuranceService {
    Assurance add(Assurance assurance);
    Assurance details(Long id);
    Assurance update(Assurance assurance);
    void delete(Long id);
    List<Assurance> list();
}
