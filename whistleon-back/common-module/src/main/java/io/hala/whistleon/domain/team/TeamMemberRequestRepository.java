package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRequestRepository extends JpaRepository<TeamMemberRequest, Long> {

  List<TeamMemberRequest> findAllByTeam(Team team);

  Optional<TeamMemberRequest> findByUserAndTeam(User user, Team team);
}