package com.josue.kodeur.xtremanalyse.security.repositories;

import com.josue.kodeur.xtremanalyse.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserMatricule(String matricule);

}