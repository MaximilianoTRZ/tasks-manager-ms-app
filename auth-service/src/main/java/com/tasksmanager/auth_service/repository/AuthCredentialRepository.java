package com.tasksmanager.auth_service.repository;

import com.tasksmanager.auth_service.entity.AuthCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthCredentialRepository extends JpaRepository<AuthCredential, Long> {
    Optional<AuthCredential> findByEmail(String email);
    boolean existsByEmail(String email);
}