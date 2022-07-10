package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;
import com.josue.kodeur.xtremanalyse.application.repositories.personnes.TypePersonneRepository;
import com.josue.kodeur.xtremanalyse.application.services.accidents.TypePersonneService;
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
public class TypePersonneServiceImpl implements TypePersonneService {

    private final TypePersonneRepository typePersonneRepository;

    @Override
    public TypePersonne add(TypePersonne typePersonne) {
        typePersonne.setCreatedAt(LocalDateTime.now());
        typePersonne.setUpdateAt(LocalDateTime.now());
        return typePersonneRepository.save(typePersonne);
    }

    @Override
    public TypePersonne update(TypePersonne typePersonne, Long id) {
        TypePersonne oldTypePersonne = typePersonneRepository.findById(id).orElse(null);
        if (oldTypePersonne != null){
            oldTypePersonne.setNom(typePersonne.getNom());
            oldTypePersonne.setUpdateAt(LocalDateTime.now());
        }
        return oldTypePersonne;
    }

    @Override
    public List<TypePersonne> listAll() {
        return typePersonneRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        typePersonneRepository.deleteById(id);
    }
}
