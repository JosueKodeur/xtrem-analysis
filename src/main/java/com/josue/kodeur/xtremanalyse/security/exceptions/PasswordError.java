package com.josue.kodeur.xtremanalyse.security.exceptions;

/**
 * @author JosueKodeur
 */

public class PasswordError extends RuntimeException{
    public PasswordError(String message) {
        super(message);
    }

    public PasswordError(){

    }
}
