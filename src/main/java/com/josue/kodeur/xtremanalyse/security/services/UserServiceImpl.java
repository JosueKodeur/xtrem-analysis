package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.exceptions.MatriculeExistException;
import com.josue.kodeur.xtremanalyse.security.repositories.RoleRepository;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public User addNewUser(String matricule, String password, String nom, boolean isActive, boolean isNotLocked, String roleName) throws MatriculeExistException {
        User currentUser = userRepository.findByUserMatricule(matricule);
        Role role = roleRepository.findByNom(roleName);
        if (currentUser != null)
            throw new MatriculeExistException("");
        User user = new User();
        user.setNom(nom);
        user.setUserMatricule(matricule);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(isActive);
        user.setNotLocked(isNotLocked);
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        userRepository.save(user);
        return user;
    }



    @Override
    public User loadUserByMatricule(String matricule) throws UsernameNotFoundException {

        if (StringUtils.isNotBlank(matricule)){
            User user = userRepository.findByUserMatricule(matricule);
            if (user == null)
                throw new UsernameNotFoundException((String.format("Matricule %s introuvable", matricule)));
            else{
                user.setLastLoginDateDisplay(user.getLastLoginDate());
                user.setLastLoginDate(new Date());
            }

            return user;
        }
        return null;
    }


    @Override
    public User userDetails(String matricule) throws NotFoundException {
        if(matricule==null)
            throw new NotFoundException("Utilisateur introuvable");
        return userRepository.findByUserMatricule(matricule);
    }

    @Override
    public User update(String matricule,
                       String password,
                       String nom,
                       Boolean isActive,
                       Boolean isNotLocked,
                       String roleName,
                       String newMatricule) throws UsernameNotFoundException, MatriculeExistException {
        User newUser = userRepository.findByUserMatricule(newMatricule);
        Role role = roleRepository.findByNom(roleName);
        if (StringUtils.isNotBlank(matricule)) {
            User currentUser = userRepository.findByUserMatricule(matricule);
            if (currentUser == null) {
                throw new UsernameNotFoundException("");
            }
            if (newUser != null && !currentUser.getId().equals(newUser.getId())) {
                throw new MatriculeExistException("");
            }
            if (!currentUser.getRoles().contains(role)) {
                currentUser.getRoles().add(role);
                currentUser.setNom(nom);
                currentUser.setUserMatricule(newMatricule);
                currentUser.setPassword(passwordEncoder.encode(password));
                currentUser.setActive(isActive);
                currentUser.setNotLocked(isNotLocked);
                return currentUser;
            }
        }

        return null;
    }

    @Override
    public User register(String matricule, String password, String nom) throws MatriculeExistException {
        User currentUser = userRepository.findByUserMatricule(matricule);
        Role role = roleRepository.findByNom("ROLE_USER");
        log.info("Entrée");
        if (currentUser != null)
            throw new MatriculeExistException("");
        User user = new User();
        user.setNom(nom);
        user.setUserMatricule(matricule);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(false);
        user.setNotLocked(false);
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void resetPassword(String email) {

    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUser(String matricule) {
        return userRepository.searchUserByUserMatricule(matricule);
    }

    @Override
    public void deleteRole(List<String> roles, String matricule) {
        if (roles!=null && !matricule.isEmpty()){

            for (String r: roles) {
                Role role = roleRepository.findByNom(r);
                User user = userRepository.findByUserMatricule(matricule);
                user.getRoles().remove(role);
            }
        }
    }

}
