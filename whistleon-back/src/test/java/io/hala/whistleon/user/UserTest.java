package io.hala.whistleon.user;

import io.hala.whistleon.domain.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(properties = {"spring.config.location=classpath:application-dev.properties"})
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatRepository userStatRepository;


    @Transactional
    @Test
    void userInsert(){
        //Given
        UserStat userStat = new UserStat();

        User user = User.builder()
                .email("kang")
                .location("incheon")
                .description("hello")
                .name("sang")
                .phoneNum("01012341234")
                .position1(Position.CAM)
                .position2(Position.CDM)
                .height(123)
                .weight(12)
                .birthday(LocalDate.now())
                .nickname("sang")
                .password("123")
                .build();

        //When
        Long userId =  userRepository.save(user).getUserId();
        Optional<User> findUser = userRepository.findById(userId);

        UserStat saveUserStat = userStatRepository.save(userStat);
        user.addStat(saveUserStat);

        //Then
        assertThat(user.getUserStat()).isNotNull();
        assertThat(saveUserStat.getStatId()).isEqualTo(user.getUserStat().getStatId());

        assertThat(findUser.isPresent()).isEqualTo(true);
        assertThat(findUser.get()).isEqualTo(user);
    }
}
