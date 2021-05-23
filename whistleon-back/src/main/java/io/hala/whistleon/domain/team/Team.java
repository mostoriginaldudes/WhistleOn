package io.hala.whistleon.domain.team;

import io.hala.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@NoArgsConstructor
@Getter
@Entity(name = "team")
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;

    @Nullable
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "stat_id")
    private TeamStat teamStat;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    @ColumnDefault("default.jpg")
    private String logo;

    @Column(name = "sido")
    private String sido;

    @Column(name = "sigungu")
    private String sigungu;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "found_date")
    private LocalDate foundDate;

    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();

    @Builder
    public Team(String name, String sido, String sigungu, String email, String description, LocalDate foundDate) {
        this.name = name;
        this.sido = sido;
        this.sigungu = sigungu;
        this.email = email;
        this.description = description;
        this.foundDate = foundDate;
    }

    public void addTeamStat(TeamStat teamStat) {
        this.teamStat = teamStat;
    }
}
