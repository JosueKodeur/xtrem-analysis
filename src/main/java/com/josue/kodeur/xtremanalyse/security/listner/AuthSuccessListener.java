package com.josue.kodeur.xtremanalyse.security.listner;

import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.services.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author JosueKodeur
 */

@Component
public class AuthSuccessListener {
    private final LoginAttemptService loginAttemptService;

    @Autowired
    public AuthSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthSuccess(AuthenticationFailureBadCredentialsEvent event) throws ExecutionException {
        Object principal = event.getAuthentication().getPrincipal();

        if (principal instanceof User){
            User user = (User) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttempt(user.getUserMatricule());
        }
    }
}
