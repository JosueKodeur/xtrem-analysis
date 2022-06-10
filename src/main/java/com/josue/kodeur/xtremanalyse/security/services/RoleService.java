package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.security.entities.Role;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface RoleService {
    Role addRole(Role role);
    void addRoleToUser(String matricule, String roleName);
    List<Role> listAll();
}
