package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.service.qna.QnaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/qna")
@RestController
public class QnaController {

  private final QnaService qnaService;

  @ApiOperation("Qna 등록")
  @PostMapping
  public ResponseEntity<?> registQna(@RequestBody QnaRegistRequestDto qnaRegistRequestDto) {
    return new ResponseEntity<>(qnaService.registQna(qnaRegistRequestDto), HttpStatus.CREATED);
  }
}
