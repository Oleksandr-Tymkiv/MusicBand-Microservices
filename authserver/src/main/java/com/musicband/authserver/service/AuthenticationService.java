package com.musicband.authserver.service;

import com.musicband.authserver.dto.AuthenticationRequest;
import com.musicband.authserver.dto.AuthenticationResponse;
import com.musicband.authserver.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authentication(AuthenticationRequest authenticationRequest);

    boolean logout(String authHeader);

    boolean checkBlacklist(String jti);
}
