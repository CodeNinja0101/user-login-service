package com.github.codeninja0101.user_identity_service.controller;

import com.github.codeninja0101.user_identity_service.model.UserEntity;
import com.github.codeninja0101.user_identity_service.service.UserService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isValid = userService.validateUser(username, password);
        return isValid ? ResponseEntity.ok("Login successful") : ResponseEntity.status(ExpiresFilter.XHttpServletResponse.SC_UNAUTHORIZED).body("Invalid Credentials");
    }

    @PostMapping("forgot_password")
    public ResponseEntity<String> forgotPassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.resetPassword(username, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }
}
