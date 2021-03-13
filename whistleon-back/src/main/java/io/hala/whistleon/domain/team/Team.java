package io.hala.whistleon.domain.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "found_date")
    private LocalDate foundDate;

    @Builder
    public Team(String name, String location, String email, String description, LocalDate foundDate) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.description = description;
        this.foundDate = foundDate;
    }

    public void addTeamStat(TeamStat teamStat) {
        this.teamStat = teamStat;
    }
}
