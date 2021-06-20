package io.hala.whistleon.service.qna;

import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaRegistResponseDto;

public interface QnaService {

  QnaRegistResponseDto registQna(QnaRegistRequestDto qnaRegistRequestDto);

}
