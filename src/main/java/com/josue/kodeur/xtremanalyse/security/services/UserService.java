package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;

import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.exceptions.MatriculeExistException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface UserService {

    User addNewUser(String matricule, String password, String nom, boolean isActive, boolean isNotLocked, String role) throws MatriculeExistException;

    User loadUserByMatricule(String matricule) throws UsernameNotFoundException;
    User userDetails(String matricule) throws NotFoundException;
    User update(String matricule,
     String password,
     String nom,
     Boolean isActive,
     Boolean isNotLocked,
     String role,
     String newMatricule) throws UsernameNotFoundException, MatriculeExistException;
    User register(String matricule, String nom, String password) throws MatriculeExistException;
    void deleteUser(long id);
    void resetPassword(String email);
    List<User> userList();
    List<User> searchUser(String matricule);
    void deleteRole(List<String> roles, String matricule);
}
