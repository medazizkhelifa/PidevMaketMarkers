package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.spring.dto.ForgotPasswordDto;
import tn.esprit.spring.dto.ResetPasswordDto;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private ForgotPasswordDto forgotPasswordDto;
    private ResetPasswordDto resetPasswordDto;

    public String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public void storeResetToken(String email, String token) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(forgotPasswordDto.getEmail()));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // generate reset token and send email to user
            user.setResetToken(token);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found for email: " + forgotPasswordDto.getEmail());
        }

    }

    public void sendResetPasswordEmail(String email, String token) {
        // Construct the reset password URL with the token
        String resetPasswordUrl = "http://example.com/reset-password?token=" + token;

        // Construct the email message
        String subject = "Reset your password";
        String body = "Click the link below to reset your password:\n" + resetPasswordUrl;

        // Send the email
        // ...
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token);
        if (user == null) {
            throw new RuntimeException("Invalid reset token");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
    }

}

