package com.josue.kodeur.xtremanalyse;


import com.josue.kodeur.xtremanalyse.application.utils.ConfigProperties;

import com.josue.kodeur.xtremanalyse.security.config.TwilioConfig;
import com.josue.kodeur.xtremanalyse.security.exceptions.ExceptionHandling;
import com.twilio.Twilio;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.File;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.SERVER_FOLDER;

@SpringBootApplication
@Import(ExceptionHandling.class)
@EnableConfigurationProperties(ConfigProperties.class)
@AllArgsConstructor
public class XtremAnalyseApplication {

    private final TwilioConfig twilioConfig;

    @PostConstruct
    public void iniTwilio(){
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(XtremAnalyseApplication.class, args);
        new File(System.getProperty(SERVER_FOLDER)+BASE_FOLDER).mkdirs();
    }

}
