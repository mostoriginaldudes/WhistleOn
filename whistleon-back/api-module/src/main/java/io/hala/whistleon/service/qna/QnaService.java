package io.hala.whistleon.service.qna;

import io.hala.whistleon.controller.dto.QnaInfoResponseDto;
import io.hala.whistleon.controller.dto.QnaListResponseDto;
import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;
import io.hala.whistleon.controller.dto.QnaReplyRequestDto;

public interface QnaService {

  QnaRegistResponseDto registQna(QnaRegistRequestDto qnaRegistRequestDto);

  QnaInfoResponseDto getQna(long qnaId);

  void registQnaReply(long qnaId, QnaReplyRequestDto qnaReplyRequestDto);

  QnaListResponseDto getQnaList(int page);
}
