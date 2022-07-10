package com.josue.kodeur.xtremanalyse.security.controller;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.config.JwtTokenProvider;
import com.josue.kodeur.xtremanalyse.security.dtos.AddForm;
import com.josue.kodeur.xtremanalyse.security.dtos.DeleteRoleForm;
import com.josue.kodeur.xtremanalyse.security.dtos.LoginForm;
import com.josue.kodeur.xtremanalyse.security.dtos.RegisterForm;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.entities.UserPrincipal;
import com.josue.kodeur.xtremanalyse.security.exceptions.ExceptionHandling;
import com.josue.kodeur.xtremanalyse.security.exceptions.MatriculeExistException;
import com.josue.kodeur.xtremanalyse.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@CrossOrigin("*")
public class UserController extends ExceptionHandling {
    private final UserService userService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public User add(@RequestBody AddForm addForm

                    ) throws UsernameNotFoundException, MatriculeExistException {
        return userService.addNewUser(addForm.getMatricule(),
                addForm.getPassword(),
                addForm.getNom(),
                Boolean.parseBoolean(addForm.getIsActive()),
                Boolean.parseBoolean(addForm.getIsNotLocked()),
                addForm.getRole());
    }

    @PostMapping("/register")
    public User register(@RequestBody AddForm addForm) throws UsernameNotFoundException, MatriculeExistException {
        return userService.register(addForm.getMatricule(), addForm.getPassword(), addForm.getNom());
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginForm loginForm){
        authenticate(loginForm.getMatricule(), loginForm.getPassword());
        User user = userService.loadUserByMatricule(loginForm.getMatricule());
        UserPrincipal userPrincipal = new UserPrincipal(user);
        user.setToken(jwtTokenProvider.generateToken(userPrincipal));
        user.setTokenValid(jwtTokenProvider.isTokenValid(user.getUserMatricule(), user.getToken()));
        return new ResponseEntity<>(user, OK);
    }

    //Eny song

    @GetMapping("/list-users")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(userService.userList(), OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody AddForm addForm) throws MatriculeExistException {
        return new ResponseEntity<>(userService.update(addForm.getMatricule(),
                addForm.getPassword(), addForm.getNom(),
                Boolean.parseBoolean(addForm.getIsActive()), Boolean.parseBoolean(addForm.getIsNotLocked()),
                addForm.getRole(), addForm.getNewMatricule()) , OK);
    }

    @PutMapping("/delete-role")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public void deleteRole(@RequestBody DeleteRoleForm deleteRoleForm) {
        if (!deleteRoleForm.getMatricule().isEmpty() && deleteRoleForm.getRoles() != null){
            userService.deleteRole(deleteRoleForm.getRoles(), deleteRoleForm.getMatricule());
        }
    }

    @GetMapping("/find/{matricule}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<User> profile(@PathVariable("matricule") String matricule) throws NotFoundException {
        return new ResponseEntity<>(userService.userDetails(matricule), OK);
    }

    @GetMapping("/search/{matricule}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    public ResponseEntity<List<User>> search(@PathVariable("matricule") String matricule) {
        return new ResponseEntity<>(userService.searchUser(matricule), OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }


    private void authenticate(String matricule, String password) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(matricule, password));
    }

}
