package io.hala.whistleon.domain.qna;

import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "qna")
public class Qna {
    @Id @GeneratedValue
    @Column(name = "qna_id")
    private Long qnaId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
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
    public Qna(String title, User user, String content){
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }
}
