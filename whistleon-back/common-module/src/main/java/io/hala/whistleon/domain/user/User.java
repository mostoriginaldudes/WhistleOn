package io.hala.whistleon.domain.user;

import io.hala.whistleon.domain.team.Team;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;


@DynamicInsert
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Nullable
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "stat_id")
  private UserStat userStat; // fk (one to one)

  @Nullable
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team; // fk (one to one)

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password; // should be encrypted

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  @ColumnDefault("NONE")
  private Role role; // [NONE, MEMBER, STAFF, LEADER]

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "phone_num")
  private String phoneNum;

  @Column(name = "sido")
  private String sido;

  @Column(name = "sigungu")
  private String sigungu;

  @Column(name = "zonecode")
  private String zonecode;

  @Column(name = "road_address")
  private String roadAddress;

  @Column(name = "height")
  private Integer height;

  @Column(name = "weight")
  private Integer weight;

  @Column(name = "nickname")
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(name = "position1")
  private Position position1; // [LB, LWB, RB, RWB, CB, CDM, CM, CAM, LW, ST, CF, RW, GK]

  @Enumerated(EnumType.STRING)
  @Column(name = "position2")
  private Position position2; // [LB, LWB, RB, RWB, CB, CDM, CM, CAM, LW, ST, CF, RW, GK]

  @Column(name = "description")
  private String description;


  public void addStat(UserStat userStat) {
    this.userStat = userStat;
  }

  public void updateUserUsingUpdateInfo(UserInfo userInfo) {
    this.nickname = userInfo.getNickname();
    this.phoneNum = userInfo.getPhoneNum();
    this.roadAddress = userInfo.getRoadAddress();
    this.sido = userInfo.getSido();
    this.sigungu = userInfo.getSigungu();
    this.zonecode = userInfo.getSigungu();
    this.height = userInfo.getHeight();
    this.weight = userInfo.getWeight();
    this.position1 = userInfo.getPosition1();
    this.position2 = userInfo.getPosition2();
    this.description = userInfo.getDescription();
  }

  public boolean hasNotTeam() {
    if (this.team != null) {
      throw new CustomException(ExceptionCode.HAS_TEAM);
    }
    return true;
  }

  public boolean hasTeamAuth(Team team) {
    if (this.role != Role.LEADER && this.role != Role.STAFF) {
      throw new CustomException(ExceptionCode.UNAUTHORIZED_TEAM_MANAGER);
    }
    if (this.getTeam() == null || !team.getEmail().equals(this.getTeam().getEmail())) {
      throw new CustomException(ExceptionCode.UNAUTHORIZED_TEAM);
    }
    return true;
  }
  public void createTeam(Team team) {
    this.team = team;
    this.role = Role.LEADER;
  }
}
