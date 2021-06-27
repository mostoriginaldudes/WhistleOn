package io.hala.whistleon.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthCode, Long> {

  Optional<AuthCode> findAuthCodeByEmail(String email);
}
