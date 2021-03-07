package io.hala.whistleon.domain.notice;

import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity(name = "notice")
public class Notice {
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long noticeId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "author")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }

    /**
     * User instance is set through this method.
     * Notice entity's user is always Admin, so this method checks user instance using checking method.
     * Otherwise this method throws Exception.
     *
     * @param user
     * @throws Exception
     */
    public void addAuthor(User user) throws Exception {
        if (checking(user)) {
            this.user = user;
        } else {
            throw new Exception();
        }
    }

    /**
     * This method checks whether user is an admin.
     *
     * @param user
     * @return true if user is an admin, else false.
     */
    private boolean checking(User user) {
        return "whistleon92@gmail.com".equals(user.getEmail()) &&
                "관리자".equals(user.getNickname()) &&
                Role.ADMIN.equals(user.getRole());
    }
}
