package com.tasksmanager.auth_service.service;

import com.tasksmanager.auth_service.dto.AuthRequest;
import com.tasksmanager.auth_service.dto.AuthResponse;
import com.tasksmanager.auth_service.dto.RegisterRequest;
import com.tasksmanager.auth_service.entity.AuthCredential;
import com.tasksmanager.auth_service.repository.AuthCredentialRepository;
import com.tasksmanager.auth_service.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        var credential = AuthCredential.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(credential);

        String jwt = jwtService.generateToken(credential.getEmail());
        return new AuthResponse(jwt);
    }

    public AuthResponse login(AuthRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        authManager.authenticate(authToken);

        String jwt = jwtService.generateToken(request.getEmail());
        return new AuthResponse(jwt);
    }
}
