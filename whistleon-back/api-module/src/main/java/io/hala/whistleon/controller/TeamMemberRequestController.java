package io.hala.whistleon.controller;

import io.hala.whistleon.service.team.TeamMemberRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/team-member-regist")
public class TeamMemberRequestController {

  private final TeamMemberRequestService teamMemberRequestService;

  @PostMapping("/team/{teamId}")
  public ResponseEntity<?> requestTeamMember(@PathVariable Long teamId) {
    teamMemberRequestService.registTeamMember(teamId);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping("/team/{teamId}")
  public ResponseEntity<?> findAllRegistTeamMembers(@PathVariable Long teamId) {
    return ResponseEntity.ok(teamMemberRequestService.findRequestTeamMembers(teamId));
  }

  @GetMapping("/team/{teamId}/user/{userId}")
  public ResponseEntity<?> findOneRegistTeamMember(@PathVariable Long teamId,
      @PathVariable Long userId) {
    return ResponseEntity.ok(teamMemberRequestService.findOneRequestTeamMember(teamId, userId));
  }
}
