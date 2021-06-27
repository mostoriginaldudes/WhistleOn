package io.hala.whistleon.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findUsersByNickname(String nickname);

  Optional<User> findUserByEmailAndPassword(String email, String password);
}
