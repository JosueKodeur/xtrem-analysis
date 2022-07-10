package com.josue.kodeur.xtremanalyse;

import com.josue.kodeur.xtremanalyse.application.utils.ConfigProperties;

import com.josue.kodeur.xtremanalyse.security.exceptions.ExceptionHandling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.SERVER_FOLDER;

@SpringBootApplication
@Import(ExceptionHandling.class)
@EnableConfigurationProperties(ConfigProperties.class)
public class XtremAnalyseApplication {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(XtremAnalyseApplication.class, args);
        new File(System.getProperty(SERVER_FOLDER)+BASE_FOLDER).mkdirs();
    }
}
