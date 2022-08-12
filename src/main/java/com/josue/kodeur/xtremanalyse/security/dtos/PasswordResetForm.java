package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.Data;

@Data
public class PasswordResetForm {
    String number;
    String username;
    String otp;
}
