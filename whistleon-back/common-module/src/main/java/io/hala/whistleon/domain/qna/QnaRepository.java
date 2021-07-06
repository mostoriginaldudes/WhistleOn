package io.hala.whistleon.domain.qna;

import io.hala.whistleon.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {

  Page<Qna> findAll(Pageable pageable);

  Page<Qna> findAllByUser(User user, Pageable pageable);
}
