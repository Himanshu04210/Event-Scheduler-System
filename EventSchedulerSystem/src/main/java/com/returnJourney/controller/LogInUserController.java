package com.returnJourney.controller;

import com.returnJourney.exception.UserException;
import com.returnJourney.model.User;
import com.returnJourney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class LogInUserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/signIn")
        public ResponseEntity<?> signInUserHandler(Authentication authentication) throws UserException {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new BadCredentialsException("Credintial are not matched...."));
            return ResponseEntity.ok(user);
        }
}
