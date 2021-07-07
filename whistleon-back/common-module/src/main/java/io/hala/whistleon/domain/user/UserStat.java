package io.hala.whistleon.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Getter
@Entity(name = "user_stat")
public class UserStat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stat_id")
  private Long statId;

  @Column(name = "win")
  @ColumnDefault("0")
  private Integer win;

  @Column(name = "draw")
  @ColumnDefault("0")
  private Integer draw;

  @Column(name = "lose")
  @ColumnDefault("0")
  private Integer lose;

  @Column(name = "mvp_count")
  @ColumnDefault("0")
  private Integer mvpCount;

  @Column(name = "manner")
  @ColumnDefault("0")
  private Double manner;

  @Column(name = "manner_sum")
  @ColumnDefault("0")
  private Integer mannerSum;

  @Column(name = "manner_vote")
  @ColumnDefault("0")
  private Integer mannerVote;

  @Column(name = "score") // 등수 구하기 위한 점수
  @ColumnDefault("0")
  private Integer score;

}
