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

    User addNewUser(String matricule
            , String password,
                    String nom,
                    boolean isActive, String role) throws MatriculeExistException;

    User loadUserByMatricule(String matricule) throws UsernameNotFoundException;

    User userDetails(String matricule) throws NotFoundException;

    User update(String matricule,
                String nom,
                String prenom,
                String phoneNumber,
                String email,
                String address,
                Boolean isActive,
                String role,
                String newMatricule) throws UsernameNotFoundException, MatriculeExistException;

    User updateProfile(String matricule,
                String nom,
                String prenom,
                String phoneNumber,
                String email,
                String address,
                String newMatricule) throws UsernameNotFoundException, MatriculeExistException;

    User register(String matricule,
                  String nom,
                  String prenom,
                  String email,
                  String phoneNumber,
                  String address,
                  String password) throws MatriculeExistException;

    void deleteUser(long id);

    void changePassword(String matricule, String password);

    List<User> userList( );

    List<User> searchUser(String matricule);

    void deleteRole(List<String> roles, String matricule);
}
