package com.josue.kodeur.xtremanalyse.security.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author JosueKodeur
 */

@Service
public class LoginAttemptService {
    private static final int MAX_NUMBER_OF_ATTEMPTS = 5;
    private static final int ATTEMPT_INCREMENT = 1;
    private LoadingCache<String, Integer> loginCache;

    public LoginAttemptService() {
        super();
        loginCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(100)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    public void evictUserFromLoginAttempt(String matricule) {
        loginCache.invalidate(matricule);
    }

    public void addUserToLoginAttempt(String matricule){
        int attempts = 0;
        try {
            attempts = ATTEMPT_INCREMENT + loginCache.get(matricule);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        loginCache.put(matricule, attempts);

    }

    public boolean hasExceeded(String matricule){
        try {
            return loginCache.get(matricule) >= MAX_NUMBER_OF_ATTEMPTS;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }
}

