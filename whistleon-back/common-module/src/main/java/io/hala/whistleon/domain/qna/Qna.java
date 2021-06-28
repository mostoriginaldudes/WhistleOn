package io.hala.whistleon.domain.qna;

import io.hala.whistleon.domain.user.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "qna")
public class Qna {

  @Id
  @GeneratedValue
  @Column(name = "qna_id")
  private Long qnaId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author")
  private User user;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "date")
  private LocalDate date;

  @OneToMany(mappedBy = "qna")
  private List<QnaReply> qnaReplies;

  @Builder
  public Qna(String title, User user, String content) {
    this.user = user;
    this.title = title;
    this.content = content;
    this.date = LocalDate.now();
    this.qnaReplies = new ArrayList<>();
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
    this.date = LocalDate.now();
  }


  public void addQnaReply(QnaReply qnaReply) {
    this.getQnaReplies().add(qnaReply);
  }
}
