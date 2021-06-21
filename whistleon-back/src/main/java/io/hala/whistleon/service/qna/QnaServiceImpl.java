package io.hala.whistleon.service.qna;

import io.hala.whistleon.common.exception.CustomException;
import io.hala.whistleon.common.exception.ExceptionCode;
import io.hala.whistleon.common.util.PrincipalHelper;
import io.hala.whistleon.controller.dto.QnaInfoResponseDto;
import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;
import io.hala.whistleon.controller.dto.QnaReplyRequestDto;
import io.hala.whistleon.controller.dto.QnaReplyResponseDto;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaReply;
import io.hala.whistleon.domain.qna.QnaReplyRepository;
import io.hala.whistleon.domain.qna.QnaRepository;
import io.hala.whistleon.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QnaServiceImpl implements QnaService {

  private final PrincipalHelper principalHelper;
  private final QnaRepository qnaRepository;
  private final QnaReplyRepository qnaReplyRepository;

  @Override
  public QnaRegistResponseDto registQna(QnaRegistRequestDto qnaRegistRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    Qna qna = qnaRegistRequestDto.toQna(loginUser);
    qnaRepository.save(qna);
    return QnaRegistResponseDto.of(qna.getQnaId());
  }

  @Override
  public QnaInfoResponseDto getQna(long qnaId) {
    Qna qna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));
    List<QnaReplyResponseDto> qnaReplies = qna.replyToResponse();
    return QnaInfoResponseDto.of(qna, qnaReplies);
  }

  @Override
  public void registQnaReply(long qnaId, QnaReplyRequestDto qnaReplyRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    Qna qna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));
    QnaReply qnaReply = qnaReplyRequestDto.toQnaReply(loginUser, qna);
    qnaReplyRepository.save(qnaReply);
    qna.addQnaReply(qnaReply);
  }
}
