package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface UserService {
    User register(User user);
    User loadUserByMatricule(String matricule) throws UsernameNotFoundException;
    User userDetails(String matricule) throws NotFoundException;
    List<User> userList();
}
