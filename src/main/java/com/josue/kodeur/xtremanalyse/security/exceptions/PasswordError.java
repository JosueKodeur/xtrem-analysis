package com.josue.kodeur.xtremanalyse.security.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * @author JosueKodeur
 */

public class PasswordError extends BadCredentialsException {
    public PasswordError(String message) {
        super(message);
    }
}
