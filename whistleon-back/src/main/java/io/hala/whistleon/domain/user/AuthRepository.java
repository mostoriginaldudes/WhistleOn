package io.hala.whistleon.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthCode, Long> {
    Optional<AuthCode> findAuthCodeByEmail(String email);
}
