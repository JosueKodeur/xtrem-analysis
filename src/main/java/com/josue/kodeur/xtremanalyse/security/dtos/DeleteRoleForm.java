package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.Data;

import java.util.List;

/**
 * @author GhostKodeur
 **/

@Data
public class DeleteRoleForm {
    String matricule;
    List<String> roles;
}
