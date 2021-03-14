package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "team_notice")
public class TeamNotice {
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long noticeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    private User author;

    @OneToMany(mappedBy = "teamNotice")
    private List<TeamNoticeReply> teamNoticeReplies = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    @Builder
    public TeamNotice(Team team, User author, String title, String content) {
        this.team = team;
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }
}
