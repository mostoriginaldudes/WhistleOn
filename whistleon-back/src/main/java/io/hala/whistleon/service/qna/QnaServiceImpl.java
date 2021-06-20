package io.hala.whistleon.service.qna;

import io.hala.whistleon.common.util.PrincipalHelper;
import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaRepository;
import io.hala.whistleon.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QnaServiceImpl implements QnaService {

  private final PrincipalHelper principalHelper;
  private final QnaRepository qnaRepository;

  @Override
  public QnaRegistResponseDto registQna(QnaRegistRequestDto qnaRegistRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    Qna qna = qnaRegistRequestDto.toQna(loginUser);
    qnaRepository.save(qna);
    return QnaRegistResponseDto.of(qna.getQnaId());
  }
}
