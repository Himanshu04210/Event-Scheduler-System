package com.returnJourney.controller;

import com.returnJourney.exception.UserException;
import com.returnJourney.model.User;
import com.returnJourney.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        return ResponseEntity.ok("Welcome into the system");
    }
    @PostMapping("/create")
    public ResponseEntity<?> createUserHandler(@Valid @RequestBody User user) throws Exception {
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getuserByEmailHandler(@PathVariable String email) throws UserException {
        ResponseEntity<?> responseEntity = null;
        userService.getUserByEmail(email);
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfileHandler(Authentication authentication) throws UserException {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<?>> getAllUsers() throws UserException {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
