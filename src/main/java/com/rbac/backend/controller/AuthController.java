package com.rbac.backend.controller;

import com.rbac.backend.dto.RegisterRequest;
import com.rbac.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String responseMessage = authService.registerUser(request);

        if (responseMessage.startsWith("Error")) {
            return ResponseEntity.badRequest().body(responseMessage); // Returns 400 Bad Request
        }

        return ResponseEntity.ok(responseMessage); // Returns 200 OK
    }
}