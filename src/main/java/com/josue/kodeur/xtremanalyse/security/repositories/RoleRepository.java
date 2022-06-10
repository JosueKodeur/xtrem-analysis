package com.josue.kodeur.xtremanalyse.security.repositories;

import com.josue.kodeur.xtremanalyse.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
}