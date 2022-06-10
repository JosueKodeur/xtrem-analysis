package com.josue.kodeur.xtremanalyse.security.controller;

import com.josue.kodeur.xtremanalyse.security.dtos.RoleToUserForm;
import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.exceptions.ExceptionHandling;
import com.josue.kodeur.xtremanalyse.security.services.RoleService;
import com.josue.kodeur.xtremanalyse.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("/api/v1/role")
@AllArgsConstructor
public class RoleController extends ExceptionHandling {

    private final RoleService roleService;

    @PostMapping("/add-role")
    @PostAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public Role save(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PostMapping("/add-role-to-user")
    @PostAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        roleService.addRoleToUser(roleToUserForm.getMatricule(), roleToUserForm.getRole());
    }

    @GetMapping("/list-roles")
    @PostAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<Role> listRoles( ){
        return roleService.listAll();
    }



}
