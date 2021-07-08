package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.TeamRegistRequestDto;
import io.hala.whistleon.controller.dto.TeamUpdateRequestDto;
import io.hala.whistleon.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/team")
public class TeamController {

  private final TeamService teamService;

  @GetMapping("/email/{email}")
  public ResponseEntity<?> checkTeamEmail(@PathVariable String email) {
    teamService.checkExistTeamEmail(email);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @PostMapping
  public ResponseEntity<?> registTeam(@ModelAttribute TeamRegistRequestDto teamRegistRequestDto) {
    teamService.registTeam(teamRegistRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @PatchMapping("/email/{email}")
  public ResponseEntity<?> updateTeam(@PathVariable String email,
      @ModelAttribute TeamUpdateRequestDto teamUpdateRequestDto) {
    teamService.updateTeam(email, teamUpdateRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
