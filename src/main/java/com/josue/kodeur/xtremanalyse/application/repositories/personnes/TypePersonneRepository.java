package com.josue.kodeur.xtremanalyse.application.repositories.personnes;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.TypePersonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePersonneRepository extends JpaRepository<TypePersonne, Long> {
    TypePersonne findByNom(String nom);
}