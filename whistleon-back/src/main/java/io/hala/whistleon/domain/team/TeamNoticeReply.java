package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "team_notice_reply")
public class TeamNoticeReply {

  @Id
  @GeneratedValue
  @Column(name = "reply_id")
  private Long replyId;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "notice_id")
  private TeamNotice teamNotice;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "author")
  private User author;

  @Column(name = "content")
  private String content;

  @Column(name = "date")
  private LocalDate date;

  @Builder
  public TeamNoticeReply(TeamNotice teamNotice, User author, String content) {
    this.teamNotice = teamNotice;
    this.author = author;
    this.content = content;
    this.date = LocalDate.now();
  }
}
