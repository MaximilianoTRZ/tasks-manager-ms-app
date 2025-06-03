package com.tasksmanager.auth_service.config;

import com.tasksmanager.auth_service.entity.AuthCredential;
import com.tasksmanager.auth_service.repository.AuthCredentialRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AuthCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        AuthCredential user = AuthCredential.builder()
                .email("test@example.com")
                .password(passwordEncoder.encode("password123"))
                .build();
        repository.save(user);
    }
}

