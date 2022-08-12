package com.josue.kodeur.xtremanalyse.application.exceptions;

/**
 * @author JosueKodeur
 */

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
