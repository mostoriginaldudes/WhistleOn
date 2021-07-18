package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

@DynamicInsert
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity(name = "team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "team_id")
  private Long teamId;

  @Nullable
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "stat_id")
  private TeamStat teamStat;

  @Column(name = "name")
  private String name;

  @Column(name = "logo")
  private String logo;

  @Column(name = "sido")
  private String sido;

  @Column(name = "sigungu")
  private String sigungu;

  @Column(name = "email")
  private String email;

  @Column(name = "description")
  private String description;

  @Column(name = "found_date")
  private LocalDate foundDate;

  @OneToMany(mappedBy = "team")
  private List<User> users = new ArrayList<>();


  public void addTeamStat(TeamStat teamStat) {
    this.teamStat = teamStat;
  }

  public void updateTeamInfo(UpdateTeamInfo teamInfo) {
    this.name = teamInfo.getName();
    this.logo = teamInfo.getLogo();
    this.sido = teamInfo.getSido();
    this.sigungu = teamInfo.getSigungu();
    this.description = teamInfo.getDescription();
    this.foundDate = teamInfo.getFoundDate();
  }
}
