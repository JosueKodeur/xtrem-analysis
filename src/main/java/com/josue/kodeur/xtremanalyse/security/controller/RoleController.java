package com.josue.kodeur.xtremanalyse.security.controller;

import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/role")
@AllArgsConstructor
@CrossOrigin("*")
public class RoleController {
    private final RoleRepository roleRepository;

    @GetMapping("/")
    public List<Role> roleList(){
        return roleRepository.findAll();
    }

}
