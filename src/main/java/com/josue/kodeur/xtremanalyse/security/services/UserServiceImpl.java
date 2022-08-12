package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.config.TwilioConfig;
import com.josue.kodeur.xtremanalyse.security.dtos.OTPStatus;
import com.josue.kodeur.xtremanalyse.security.dtos.PasswordResetResponse;
import com.josue.kodeur.xtremanalyse.security.entities.Role;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.exceptions.MatriculeExistException;
import com.josue.kodeur.xtremanalyse.security.repositories.RoleRepository;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
    private final OTPService otpService;
    private final TwilioConfig twilioConfig;


    @Override
    public User addNewUser(String matricule, String password, String nom, boolean isActive, String roleName) throws MatriculeExistException {
        User currentUser = userRepository.findByUserMatricule(matricule);
        Role role = roleRepository.findByNom(roleName);
        if (currentUser != null)
            throw new MatriculeExistException("");
        User user = new User();
        user.setNom(nom);
        user.setUserMatricule(matricule);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(isActive);
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
                       String nom,
                       String prenom,
                       String phoneNumber,
                       String email,
                       String address,
                       Boolean isActive,
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
            currentUser.setNom(nom);
            currentUser.setPrenom(prenom);
            currentUser.setAddress(address);
            currentUser.setEmail(email);
            currentUser.setPhoneNumber(phoneNumber);
            currentUser.setUserMatricule(newMatricule);
            currentUser.setIsActive(isActive);
            if (!currentUser.getRoles().contains(role)) {
                currentUser.getRoles().add(role);
            }

            if (isActive==true){
                PhoneNumber receiver = new PhoneNumber(currentUser.getPhoneNumber());
                PhoneNumber sender = new PhoneNumber(twilioConfig.getTrialNumber());
                String optMessage = "\nCher utilisateur, votre compte Xtrem-analyse a été activé par l'administration.";
                Message.creator(receiver, sender, optMessage).create();

            }
            return currentUser;
        }

        return null;
    }

    @Override
    public User updateProfile(String matricule, String nom, String prenom, String phoneNumber, String email, String address, String newMatricule) throws UsernameNotFoundException, MatriculeExistException {
        User newUser = userRepository.findByUserMatricule(newMatricule);
        if (StringUtils.isNotBlank(matricule)) {
            User currentUser = userRepository.findByUserMatricule(matricule);
            if (currentUser == null) {
                throw new UsernameNotFoundException("");
            }
            if (newUser != null && !currentUser.getId().equals(newUser.getId())) {
                throw new MatriculeExistException("");
            }
            currentUser.setNom(nom);
            currentUser.setPrenom(prenom);
            currentUser.setAddress(address);
            currentUser.setEmail(email);
            currentUser.setPhoneNumber(phoneNumber);
            currentUser.setUserMatricule(newMatricule);
            return currentUser;

        }

        return null;
    }

    @Override
    public User register(String matricule,
                         String nom,
                         String prenom,
                         String email,
                         String phoneNumber,
                         String address,
                         String password) throws MatriculeExistException {
        User currentUser = userRepository.findByUserMatricule(matricule);
        Role role = roleRepository.findByNom("USER");
        if (currentUser != null)
            throw new MatriculeExistException("");
        User user = new User();
        user.setNom(nom);
        user.setUserMatricule(matricule);
        user.setPrenom(prenom);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(false);
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
    public void changePassword(String matricule, String password) {
        var user = userRepository.findByUserMatricule(matricule);
        user.setPassword(passwordEncoder.encode(password));
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
