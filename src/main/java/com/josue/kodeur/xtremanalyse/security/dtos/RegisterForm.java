package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.Data;

/**
 * @author GhostKodeur
 **/

@Data
public class RegisterForm {
    private String matricule;
    private String password;
    private Boolean isActive;
    private Boolean isNotLocked;
    private String role;
    private String newMatricule;
}
