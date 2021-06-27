package io.hala.whistleon.domain.match;

import io.hala.whistleon.domain.user.User;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

@DynamicInsert
@Getter
@NoArgsConstructor
@Entity(name = "match")
public class Match {

  @Id
  @GeneratedValue
  @Column(name = "match_id")
  private Long matchId;

  @Nullable
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "mom_user")
  private User momUser;

  @Column(name = "kickoff_time")
  private LocalDateTime kickoffTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  @ColumnDefault("BEFORE")
  private MatchStatus status;

  @Column(name = "location")
  private String location;

  @Column(name = "stadium")
  private String stadium;

  @Column(name = "home_score")
  @ColumnDefault("0")
  private Integer homeScore;

  @Column(name = "away_score")
  @ColumnDefault("0")
  private Integer awayScore;

  @Builder
  public Match(LocalDateTime kickoffTime, String location, String stadium) {
    this.kickoffTime = kickoffTime;
    this.location = location;
    this.stadium = stadium;
  }

  /**
   * After match finished, Leader can update homeScore and awayScore. We will give points to team,
   * if they update score.
   *
   * @param homeScore
   * @param awayScore
   * @return random points(1~100)
   * @throws Exception
   */
  public int updateScore(int homeScore, int awayScore) throws Exception {
    if (homeScore >= 0) {
      this.homeScore = homeScore;
    } else {
      // I will update Exception about score.
      throw new Exception();
    }

    if (awayScore >= 0) {
      this.awayScore = awayScore;
    } else {
      // I will update Exception about score.
      throw new Exception();
    }

    return 1;
  }

  /**
   * Leader can give mom title to some user using this method. We will also give some points if they
   * update momPlayer.
   *
   * @param user
   * @return points(1 ~ 100)
   */
  public int updateMomPlayer(User user) {
    this.momUser = user;
    return 1;
  }
}
