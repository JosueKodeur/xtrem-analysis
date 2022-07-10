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
 * @author GhostKodeur
 */

@ControllerAdvice
@ResponseBody
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    private static final String ACCOUNT_DISABLED = "Votre compte est " +
            "temporairement désactivé, veuillez Contactez la direction.";

    private static final String ACCOUNT_LOCKED = "Votre compte est bloqué, veuillez Contactez la direction.";

    private static final String ACCESS_DENIED = "Vous n'êtes pas autorisé à acceder à cette ressource";

    private static final String MATRICULE_NOT_FOUND = "Matricule introuvable";
    private static final String MATRICULE_EXIST = "Le matricule existe déjà";

    private static final String TOKEN_EXPIRED = "Veuillez vous reconnecter, votre est session terminée.";
    private static final String CREDENTIAL_ERROR = "Mot de passe Incorrect, " +
            "Réessayez. Si vous avez oublié votre adresse veuillez contactez la direction";


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

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<HttpResponse> lockedAccount(){
        return createResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(MatriculeExistException.class)
    public ResponseEntity<HttpResponse> matriculeExist(){
        return createResponse(HttpStatus.BAD_REQUEST, MATRICULE_EXIST);
    }


    @ExceptionHandler(PasswordError.class)
    public ResponseEntity<HttpResponse> passwordError(){
        return createResponse(HttpStatus.BAD_REQUEST, CREDENTIAL_ERROR);
    }

    @ExceptionHandler(value = SessionExpired.class)
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
