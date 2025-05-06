package com.musicband.authserver.service.impl;

import com.musicband.authserver.config.JwtService;
import com.musicband.authserver.config.TokenBlacklistService;
import com.musicband.authserver.dto.AuthenticationRequest;
import com.musicband.authserver.dto.AuthenticationResponse;
import com.musicband.authserver.dto.RegisterRequest;
import com.musicband.authserver.entity.User;
import com.musicband.authserver.entity.role.Role;
import com.musicband.authserver.exceptions.UserAlreadyExistsException;
import com.musicband.authserver.repository.UserRepository;
import com.musicband.authserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){
        if(userRepository.findUserByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email already exists");
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .dateOfBirth(registerRequest.getDateOfBirth())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generatorJwt(user);
        return AuthenticationResponse
                .builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest){
        UserDetails user = (UserDetails)  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
        ).getPrincipal();
        var jwt = jwtService.generatorJwt(user);
        return AuthenticationResponse
                .builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public boolean logout(String authHeader) {
        boolean isLoggedOut = false;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklist(token);
            isLoggedOut = true;
        }
        return isLoggedOut;
    }

    @Override
    public boolean checkBlacklist(String jti) {
        return tokenBlacklistService.isBlacklisted(jti);
    }
}
