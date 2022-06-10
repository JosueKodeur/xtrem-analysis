package com.josue.kodeur.xtremanalyse.security.services;

import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.security.entities.User;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private LoginAttemptService loginAttemptService;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }


    @Override
    public User loadUserByMatricule(String matricule) throws UsernameNotFoundException {
        User user = userRepository.findByUserMatricule(matricule);
        if (user == null)
            throw new UsernameNotFoundException((String.format("Matricule %s introuvable", matricule)));
        else{
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
        }

        return user;
    }

    private void validateLoginAttempt(User user) {
        if (user.getIsNotLocked()){
            if (loginAttemptService.hasExceeded(user.getUserMatricule())){
                user.setIsNotLocked(false);
            } else {
                user.setIsNotLocked(true);
            }
        }else {
            loginAttemptService.evictUserFromLoginAttempt(user.getUserMatricule());

        }
    }


    @Override
    public User userDetails(String matricule) throws NotFoundException {
        if(matricule==null)
            throw new NotFoundException("Utilisateur introuvable");
        return userRepository.findByUserMatricule(matricule);
    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

}
