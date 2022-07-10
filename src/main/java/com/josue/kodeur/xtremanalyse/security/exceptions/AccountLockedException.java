package com.josue.kodeur.xtremanalyse.security.exceptions;

/**
 * @author GhostKodeur
 **/

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message) {
        super(message);
    }
}
