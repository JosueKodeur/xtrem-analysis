package com.josue.kodeur.xtremanalyse.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetResponse {
    private OTPStatus status;
    private String message;
}
