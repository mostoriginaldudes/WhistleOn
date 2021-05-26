package io.hala.whistleon.domain.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.service.user.UserService;
import org.assertj.core.api.Assertions;
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
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    void userInsert(){
        //Given
        UserStat userStat = new UserStat();

        User user = User.builder()
                .email("kang")
                .sido("인천광역시")
                .sigungu("미추홀구")
                .roadAddress("인천광역시 미추홀구 문학동 519-18")
                .zonecode("11123")
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
        user.addStat(userStat);
        Long userId =  userRepository.save(user).getUserId();
        Optional<User> findUser = userRepository.findById(userId);


        //Then
        assertThat(user.getUserStat()).isNotNull();
        assertThat(userStat.getStatId()).isEqualTo(user.getUserStat().getStatId());

        assertThat(findUser.isPresent()).isEqualTo(true);
        assertThat(findUser.get()).isEqualTo(user);
    }

    @Test
    void checkNicknameTest() {
        String existNickname = "강상우";
        assertThatThrownBy(() -> userService.checkDuplicateNickname(existNickname))
                .isInstanceOf(CustomException.class);

        String noneExistNickname = "neverEverUseThisNickname";
        assertThatCode(() -> userService.checkDuplicateNickname(noneExistNickname))
                .doesNotThrowAnyException();
    }
}
