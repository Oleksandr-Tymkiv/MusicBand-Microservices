package com.musicband.authserver.controllers;

import com.musicband.authserver.dto.AuthenticationRequest;
import com.musicband.authserver.dto.RegisterRequest;
import com.musicband.authserver.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.register(registerRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.authentication(authenticationRequest));
    }
}