package com.josue.kodeur.xtremanalyse.security.exceptions;

/**
 * @author GhostKodeur
 **/

public class MatriculeExistException extends RuntimeException{
    public MatriculeExistException(String message) {
        super(message);
    }
}
