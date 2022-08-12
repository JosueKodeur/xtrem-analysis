package com.josue.kodeur.xtremanalyse.security.services;


import com.josue.kodeur.xtremanalyse.security.config.TwilioConfig;
import com.josue.kodeur.xtremanalyse.security.dtos.OTPStatus;
import com.josue.kodeur.xtremanalyse.security.dtos.PasswordResetForm;
import com.josue.kodeur.xtremanalyse.security.dtos.PasswordResetResponse;
import com.josue.kodeur.xtremanalyse.security.repositories.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@AllArgsConstructor
public class OTPService {

    private final TwilioConfig twilioConfig;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public PasswordResetResponse sendOTPPasswordReset(String username){
        PasswordResetResponse response = null;
        try {
            var user = userRepository.findByUserMatricule(username);
            var number = user.getPhoneNumber();
            PhoneNumber receiver = new PhoneNumber(number);
            PhoneNumber sender = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();
            String optMessage = "\nCher utilisateur, votre mot de passe" +
                    " de récupération est ##" + otp + "##. Utiliser ce mot de passe" +
                    " pour changer votre mot de passe.\n Attention le mot de passe doit être immédiatement changé.\n" +
                    "Envoyé depuis Xtrem-analyse";
            Message message = Message.creator(
                            receiver,
                            sender,
                            optMessage)
                    .create();

            response = new PasswordResetResponse(OTPStatus.ENVOYE, "Success");
            user.setPassword(passwordEncoder.encode(otp));


        }catch (Exception e){
            response = new PasswordResetResponse(OTPStatus.ERREUR, "Votre mot de passe de récupération n'a pas été envoyé");
        }
        return response;
    }

    private String generateOTP(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
