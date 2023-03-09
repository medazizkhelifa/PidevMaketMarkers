package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.dto.LoginDto;
import tn.esprit.spring.dto.RegisterDto;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthRestController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("login")
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
//        String username = loginDto.getUserName();
//        String password = loginDto.getPassword();
//        // You can replace the following block with your own logic for user authentication
//        if ("Ahmed".equals(username) && "Ahmed".equals(password)) {
//            session.setAttribute("loggedInUser", username);
//            return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        String username = loginDto.getUserName();
        String password = loginDto.getPassword();
        try {
            User user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("loggedInUser", username);
                return ResponseEntity.ok("User logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Error: Role not found."));
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
