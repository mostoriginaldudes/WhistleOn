package io.hala.whistleon.domain.match;

import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@Getter
@NoArgsConstructor
@Entity(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "vote_id")
    private Long voteId;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("HOLD")
    @Column(name = "status")
    private VoteStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Vote(Match match, User user) {
        this.match = match;
        this.user = user;
    }

    /**
     * when the user votes, this method is called
     * @param status
     */
    public void updateVote(VoteStatus status) {
        this.status = status;
    }
}
