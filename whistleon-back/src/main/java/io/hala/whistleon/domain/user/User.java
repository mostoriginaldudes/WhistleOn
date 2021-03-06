package io.hala.whistleon.domain.user;

import io.hala.whistleon.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;


@DynamicInsert
@Getter
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Nullable
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "stat_id")
    private UserStat userStat; // fk (one to one)

    @Nullable
    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team; // fk (one to one)

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password; // should be encrypted

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @ColumnDefault("NONE")
    private Role role; // [NONE, MEMBER, STAFF, LEADER]

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "location")
    private String location;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "position1")
    private Position position1; // [LB, LWB, RB, RWB, CB, CDM, CM, CAM, LW, ST, CF, RW, GK]

    @Enumerated(EnumType.STRING)
    @Column(name = "position2")
    private Position position2; // [LB, LWB, RB, RWB, CB, CDM, CM, CAM, LW, ST, CF, RW, GK]

    @Column(name = "description")
    private String description;

    @Builder
    public User(String email, String name, String password, LocalDate birthday, String phoneNum, String location,
                Integer height, Integer weight, String nickname, Position position1, Position position2, String description) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.location = location;
        this.height = height;
        this.weight = weight;
        this.nickname = nickname;
        this.position1 = position1;
        this.position2 = position2;
        this.description = description;
    }

    public void addStat(UserStat userStat) {
        this.userStat = userStat;
    }
}
