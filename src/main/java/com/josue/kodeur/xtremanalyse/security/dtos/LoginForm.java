package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.Data;

/**
 * @author GhostKodeur
 **/

@Data
public class LoginForm {
    private String matricule;
    private String password;
}
