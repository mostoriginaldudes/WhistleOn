package io.hala.whistleon.service.qna;

import io.hala.whistleon.controller.dto.QnaInfoResponseDto;
import io.hala.whistleon.controller.dto.QnaListDto;
import io.hala.whistleon.controller.dto.QnaListResponseDto;
import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;
import io.hala.whistleon.controller.dto.QnaReplyRequestDto;
import io.hala.whistleon.controller.dto.QnaReplyResponseDto;
import io.hala.whistleon.controller.dto.UpdateQnaReplyRequestDto;
import io.hala.whistleon.controller.dto.UpdateQnaRequestDto;
import io.hala.whistleon.domain.qna.Qna;
import io.hala.whistleon.domain.qna.QnaReply;
import io.hala.whistleon.domain.qna.QnaReplyRepository;
import io.hala.whistleon.domain.qna.QnaRepository;
import io.hala.whistleon.domain.user.Role;
import io.hala.whistleon.domain.user.User;
import io.hala.whistleon.exception.CustomException;
import io.hala.whistleon.exception.ExceptionCode;
import io.hala.whistleon.service.PrincipalHelper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QnaServiceImpl implements QnaService {

  private static final int PAGE_SIZE = 10;
  private static final String ORDER_COLUMN = "date";

  private final PrincipalHelper principalHelper;
  private final QnaRepository qnaRepository;
  private final QnaReplyRepository qnaReplyRepository;

  @Transactional
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

    return createQnaInfoResponse(qna);
  }

  @Transactional
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
        .findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Direction.DESC, ORDER_COLUMN)));

    return createQnaListResponseDto(qnasAndPages, page);
  }

  @Override
  public QnaListResponseDto getMyQnaList(int page) {
    User loginUser = principalHelper.getLoginUser();

    Page<Qna> qnasAndPages = qnaRepository
        .findAllByUser(loginUser,
            PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Direction.DESC, ORDER_COLUMN)));

    return createQnaListResponseDto(qnasAndPages, page);
  }

  @Transactional
  @Override
  public void updateQna(long qnaId, UpdateQnaRequestDto updateQnaRequestDto) {
    User loginUser = principalHelper.getLoginUser();
    Qna qna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));
    if (isQnaAuthor(loginUser, qna.getUser())) {
      qna.update(updateQnaRequestDto.getTitle(), updateQnaRequestDto.getContent());
    }
  }

  @Transactional
  @Override
  public void deleteQna(long qnaId) {
    User loginUser = principalHelper.getLoginUser();
    Qna qna = qnaRepository.findById(qnaId)
        .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));

    if (canDeleteQna(loginUser, qna.getUser())) {
      qnaRepository.delete(qna);
    }
  }

  @Transactional
  @Override
  public void updateQnaReply(long qnaId, long replyId,
      UpdateQnaReplyRequestDto updateQnaReplyRequestDto) {
    User loginUser = principalHelper.getLoginUser();

    // Todo authentication 부분 role hierarchy로 수정할 예정
    if (isQnaReplyAuthor(loginUser)) {
      QnaReply qnaReply = qnaReplyRepository.findById(replyId)
          .orElseThrow(() -> new CustomException(ExceptionCode.RESOURCES_NOT_EXIST));

      if (isMatchQnaAndReply(qnaId, qnaReply.getQna().getQnaId())) {
        qnaReply.update(updateQnaReplyRequestDto.getContent());
      }
    }
  }

  /**
   * 이 아래로는 private method
   */

  private boolean isMatchQnaAndReply(long formQnaId, long qnaId) {
    if (formQnaId != qnaId) {
      throw new CustomException(ExceptionCode.INVALID_FORM_DATA);
    }
    return true;
  }

  private boolean isQnaReplyAuthor(User loginUser) {
    if (loginUser.getRole() != Role.ADMIN) {
      throw new CustomException(ExceptionCode.UNAUTHENTICATED_AUTHOR);
    }
    return true;
  }

  private boolean canDeleteQna(User loginUser, User qnaAuthor) {
    if (loginUser == qnaAuthor) {
      return true;
    }
    if (loginUser.getRole() == Role.ADMIN) {
      return true;
    }
    throw new CustomException(ExceptionCode.UNAUTHENTICATED_AUTHOR);
  }

  private boolean isQnaAuthor(User loginUser, User qnaAuthor) {
    if (loginUser != qnaAuthor) {
      throw new CustomException(ExceptionCode.UNAUTHENTICATED_AUTHOR);
    }
    return true;
  }

  private QnaInfoResponseDto createQnaInfoResponse(Qna qna) {
    List<QnaReplyResponseDto> qnaReplies = qna.getQnaReplies()
        .stream()
        .map(this::toQnaReplyResponse)
        .collect(Collectors.toList());

    return QnaInfoResponseDto.of(qna, qnaReplies);
  }

  private QnaReplyResponseDto toQnaReplyResponse(QnaReply qnaReply) {
    return QnaReplyResponseDto.builder()
        .replyId(qnaReply.getReplyId())
        .replier(qnaReply.getUser().getNickname())
        .date(qnaReply.getDate())
        .content(qnaReply.getContent())
        .build();
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
