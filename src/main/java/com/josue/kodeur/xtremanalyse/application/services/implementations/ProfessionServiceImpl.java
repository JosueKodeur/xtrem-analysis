package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Profession;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.ProfessionRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author GhostKodeur
 **/

@Service
@Transactional
@AllArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;

    @Override
    public Profession add(Profession profession) {
        profession.setCreatedAt(LocalDateTime.now());
        profession.setUpdateAt(LocalDateTime.now());
        return professionRepository.save(profession);
    }

    @Override
    public Profession update(Profession profession, Long id) {
        Profession oldProfession = professionRepository.findById(id).orElse(null);
        if (oldProfession != null){
            oldProfession.setNom(profession.getNom());
            oldProfession.setDescription(profession.getDescription());
            oldProfession.setUpdateAt(LocalDateTime.now());
        }
        return oldProfession;
    }

    @Override
    public List<Profession> listAll() { return professionRepository.findAll(); }

    @Override
    public void delete(Long id) { professionRepository.deleteById(id); }
}
