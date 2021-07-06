package io.hala.whistleon.domain.qna;

import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "qna_reply")
public class QnaReply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reply_id")
  private Long replyId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "qna_id")
  private Qna qna;

  @Column(name = "content")
  private String content;

  @Column(name = "date")
  private LocalDate date;

  @Builder
  public QnaReply(User user, Qna qna, String content) {
    this.user = user;
    this.qna = qna;
    this.content = content;
    this.date = LocalDate.now();
  }

  public void update(String content) {
    this.content = content;
    this.date = LocalDate.now();
  }
}
