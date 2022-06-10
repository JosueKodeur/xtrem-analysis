package com.josue.kodeur.xtremanalyse.security.controller;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.config.JwtTokenProvider;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.entities.UserPrincipal;
import com.josue.kodeur.xtremanalyse.security.exceptions.ExceptionHandling;
import com.josue.kodeur.xtremanalyse.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.JWT_HEADER;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author JosueKodeur
 */

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController extends ExceptionHandling {
    private final UserService userService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam("matricule") String matricule,
                                      @RequestParam("password") String password){
        authenticate(matricule, password);
        User user = userService.loadUserByMatricule(matricule);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        HttpHeaders jwtHeader = getHeader(userPrincipal);
        return new ResponseEntity<>(user, jwtHeader, OK);
    }

    @GetMapping("/list-users")
    @PostAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<User> allUsers(){
        return userService.userList();
    }

    @GetMapping("/profile")
    public User profile(@RequestParam("matricule") String matricule) throws NotFoundException {
        return userService.userDetails(matricule);
    }


    private void authenticate(String matricule, String password) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(matricule, password));
    }

    HttpHeaders getHeader(UserPrincipal user){
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_HEADER, jwtTokenProvider.generateToken(user));
        return headers;
    }
}
