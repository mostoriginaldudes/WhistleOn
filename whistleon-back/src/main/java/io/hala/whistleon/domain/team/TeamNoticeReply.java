package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
