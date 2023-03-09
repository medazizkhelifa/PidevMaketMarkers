package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.dto.ForgotPasswordDto;
import tn.esprit.spring.dto.ResetPasswordDto;
import tn.esprit.spring.service.ForgotPasswordService;

@RestController
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        try {
            String email = forgotPasswordDto.getEmail();
            String token = forgotPasswordService.generateToken();
            forgotPasswordService.storeResetToken(email, token);
            forgotPasswordService.sendResetPasswordEmail(email, token);
            return ResponseEntity.ok("Reset password email sent");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending reset password email: " + e.getMessage());
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        String token = resetPasswordDto.getToken();
        String newPassword = resetPasswordDto.getNewPassword();
        forgotPasswordService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }
}

