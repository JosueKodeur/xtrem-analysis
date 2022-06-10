package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.repositories.RoleRepository;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role addRole(Role role) {
        String roleName = "ROLE_"+role.getNom().toUpperCase();
        role.setNom(roleName);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String matricule, String roleName) {
        Role role = roleRepository.findByNom(roleName);
        User user = userRepository.findByUserMatricule(matricule);
        if (user!= null && !user.getRoles().contains(role)){
            user.getRoles().add(role);
        }
    }

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }
}
