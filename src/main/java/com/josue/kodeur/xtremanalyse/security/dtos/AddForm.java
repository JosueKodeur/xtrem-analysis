package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author GhostKodeur
 **/

@Data
public class AddForm {
    String matricule;
    String newMatricule;
    String password;
    String nom;
    String isActive;
    String isNotLocked;
    String role;
}
