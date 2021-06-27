package io.hala.whistleon.service.qna;

import io.hala.whistleon.controller.dto.QnaInfoResponseDto;
import io.hala.whistleon.controller.dto.QnaListDto;
import io.hala.whistleon.controller.dto.QnaListResponseDto;
import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;
import io.hala.whistleon.controller.dto.QnaReplyRequestDto;
import io.hala.whistleon.controller.dto.QnaReplyResponseDto;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaReply;
import io.hala.whistleon.domain.qna.QnaReplyRepository;
import io.hala.whistleon.domain.qna.QnaRepository;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import io.hala.whistleon.service.PrincipalHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    List<QnaReplyResponseDto> qnaReplies = new ArrayList<>();
    for (QnaReply qnaReply : qna.getQnaReplies()) {
      qnaReplies.add(QnaReplyResponseDto.builder()
          .replyId(qnaReply.getReplyId())
          .replier(qnaReply.getUser().getNickname())
          .date(qnaReply.getDate())
          .content(qnaReply.getContent())
          .build());
    }
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

  @Override
  public QnaListResponseDto getQnaList(int page) {
    Page<Qna> qnasAndPages = qnaRepository
        .findAll(PageRequest.of(page - 1, 10, Sort.by(Direction.DESC, "date")));
    List<Qna> qnas = qnasAndPages.getContent();

    List<QnaListDto> qnaListDtos = new ArrayList<>();
    for (Qna qna : qnas) {
      qnaListDtos.add(QnaListDto
          .of(qna.getQnaId(), qna.getTitle(), qna.getUser().getNickname(), qna.getDate()));
    }


  private QnaListResponseDto createQnaListResponseDto(Page<Qna> qnasAndPages, int page) {
    List<QnaListDto> qnaListDtos = qnasAndPages.getContent()
        .stream()
        .map(this::toQnaListDto)
        .collect(Collectors.toList());

    return QnaListResponseDto
        .of(qnaListDtos, page, qnasAndPages.hasNext(), qnasAndPages.getTotalPages());
  }

  private QnaListDto toQnaListDto(Qna qna) {
    return QnaListDto
        .of(qna.getQnaId(), qna.getTitle(), qna.getUser().getNickname(), qna.getDate());
  }
}
