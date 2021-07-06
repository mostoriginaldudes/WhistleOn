package io.hala.whistleon.controller;

import io.hala.whistleon.controller.dto.QnaRegistRequestDto;
import io.hala.whistleon.controller.dto.QnaReplyRequestDto;
import io.hala.whistleon.controller.dto.UpdateQnaReplyRequestDto;
import io.hala.whistleon.controller.dto.UpdateQnaRequestDto;
import io.hala.whistleon.service.qna.QnaService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @ApiOperation("Qna 한개 가져오기")
  @GetMapping("/{qnaId}")
  public ResponseEntity<?> getQna(@PathVariable long qnaId) {
    return ResponseEntity.ok(qnaService.getQna(qnaId));
  }

  @ApiOperation("Qna에 대한 답글 등록")
  @PostMapping("/{qnaId}/reply")
  public ResponseEntity<?> registQnaReply(@PathVariable int qnaId,
      @RequestBody QnaReplyRequestDto qnaReplyRequestDto) {
    qnaService.registQnaReply(qnaId, qnaReplyRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @ApiOperation("Qna 목록가져오기")
  @GetMapping
  public ResponseEntity<?> getQnaList(@Valid @Min(1) @RequestParam(defaultValue = "1") int page) {
    return ResponseEntity.ok(qnaService.getQnaList(page));
  }

  @ApiOperation("내가 작성한 qna 가져오기")
  @GetMapping("/my")
  public ResponseEntity<?> getMyQnaList(@Valid @Min(1) @RequestParam(defaultValue = "1") int page) {
    return ResponseEntity.ok(qnaService.getMyQnaList(page));
  }

  @ApiOperation("내가 작성한 qna 수정")
  @PatchMapping("/{qnaId}")
  public ResponseEntity<?> updateQna(@PathVariable long qnaId,
      @RequestBody UpdateQnaRequestDto updateQnaRequestDto) {
    qnaService.updateQna(qnaId, updateQnaRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @ApiOperation("qna 삭제")
  @DeleteMapping("/{qnaId}")
  public ResponseEntity<?> deleteQna(@PathVariable long qnaId) {
    qnaService.deleteQna(qnaId);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @ApiOperation("qna reply 수정")
  @PatchMapping("/{qnaId}/reply/{replyId}")
  public ResponseEntity<?> updateQnaReply(@PathVariable long qnaId, @PathVariable long replyId,
      @RequestBody
          UpdateQnaReplyRequestDto updateQnaReplyRequestDto) {
    qnaService.updateQnaReply(qnaId, replyId, updateQnaReplyRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @ApiOperation("qna reply 삭제")
  @DeleteMapping("/{qnaId}/reply/{replyId}")
  public ResponseEntity<?> deleteQnaReply(@PathVariable long qnaId, @PathVariable long replyId) {
    qnaService.deleteQnaReply(qnaId, replyId);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
