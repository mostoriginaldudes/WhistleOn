package io.hala.whistleon.domain.match;

import io.hala.whistleon.domain.team.Team;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "team_match")
public class TeamMatch {

  @Id
  @GeneratedValue
  @Column(name = "team_match_id")
  private Long teamMatchId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  private Team team;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "match_id")
  private Match match;


  @Enumerated(EnumType.STRING)
  @Column(name = "team_status")
  private TeamStatus teamStatus; // [HOME, AWAY]

  @Builder
  public TeamMatch(Team team, Match match, TeamStatus teamStatus) {
    this.team = team;
    this.match = match;
    this.teamStatus = teamStatus;
  }
}
