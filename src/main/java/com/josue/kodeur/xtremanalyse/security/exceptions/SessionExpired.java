package com.josue.kodeur.xtremanalyse.security.exceptions;

/**
 * @author GhostKodeur
 **/

public class SessionExpired extends RuntimeException{
    public SessionExpired(String message) {
        super(message);
    }
}
