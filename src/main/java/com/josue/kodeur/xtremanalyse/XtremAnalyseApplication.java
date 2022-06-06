package com.josue.kodeur.xtremanalyse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class XtremAnalyseApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtremAnalyseApplication.class, args);
    }

}
