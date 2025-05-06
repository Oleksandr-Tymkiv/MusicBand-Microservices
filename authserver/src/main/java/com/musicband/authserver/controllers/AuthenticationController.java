package com.musicband.authserver.controllers;

import com.musicband.authserver.dto.AuthenticationRequest;
import com.musicband.authserver.dto.RegisterRequest;
import com.musicband.authserver.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return authenticationService.logout(authHeader)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Logged out successful")
                : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Logged out failed");
    }

    @GetMapping("/check-blacklist/{jti}")
    public ResponseEntity<?> checkBlacklist(@PathVariable String jti) {
        return authenticationService.checkBlacklist(jti)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body(true)
                : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(false);

    }
}