package io.hala.whistleon.service.user;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.controller.dto.SignupRequestDto;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.domain.user.UserRepository;
import io.hala.whistleon.domain.user.UserStat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void signup(SignupRequestDto signupRequestDto) {
        User user = signupRequestDto.toUser();
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            throw new CustomException(ExceptionCode.DUPLICATE_DATA);
        }
        user.addStat(new UserStat());
        userRepository.save(user);
    }
}
