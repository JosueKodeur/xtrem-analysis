package com.josue.kodeur.xtremanalyse.security.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.josue.kodeur.xtremanalyse.security.entities.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author JosueKodeur
 */

@ControllerAdvice
@ResponseBody
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    private static final String ACCOUNT_DISABLED = "Votre compte est " +
            "temporairement désactivé, veuillez Contactez la direction.";

    private static final String ACCESS_DENIED = "Vous devriez vous connecter pour accéder à cette ressource";

    private static final String MATRICULE_NOT_FOUND = "Matricule introuvable";

    private static final String TOKEN_EXPIRED = "Session terminée.";


    @ExceptionHandler(value = DisabledException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponse> accountDisabled(){
        return createResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> usernameNotFound(){
        return createResponse(HttpStatus.NOT_FOUND, MATRICULE_NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDenied(){
        return createResponse(HttpStatus.UNAUTHORIZED, ACCESS_DENIED);
    }

    @ExceptionHandler(PasswordError.class)
    public ResponseEntity<HttpResponse> passwordError(){
        return createResponse(HttpStatus.BAD_REQUEST, TOKEN_EXPIRED);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<HttpResponse> tokenExpired(){
        return createResponse(HttpStatus.UNAUTHORIZED, TOKEN_EXPIRED);
    }


    private ResponseEntity<HttpResponse> createResponse(HttpStatus status, String message) {
        HttpResponse httpResponse = new HttpResponse(status.value(), status, status.getReasonPhrase().toUpperCase(),
                message.toUpperCase());
        return new  ResponseEntity<>(httpResponse, status);
    }



}
