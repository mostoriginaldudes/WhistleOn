package io.hala.whistleon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  /**
   * Todo 익셉션코드 구체화해서 더 적어야됨
   */
  RESOURCES_NOT_EXIST(HttpStatus.NOT_FOUND, "요청하신 정보가 존재하지 않습니다"),
  INVALID_FORM_DATA(HttpStatus.BAD_REQUEST, "요청하신 정보가 유효하지 않습니다."),
  FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 과정 중에 실패하였습니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),

  /**
   * 401 UNAUTHORIZED
   */
  UNAUTHORIZED_AUTH_CODE(HttpStatus.UNAUTHORIZED, "메일을 통한 인증정보 오류"),
  UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "계정 정보 오류"),

  /**
   * 403 FORBIDDEN
   */
  UNAUTHENTICATED_AUTHOR(HttpStatus.FORBIDDEN, "권한이 없습니다."),

  /**
   * 409 CONFLICT
   */
  HAS_TEAM(HttpStatus.CONFLICT, "이미 소속된 팀이 있습니다."),
  DUPLICATE_TEAM_EMAIL(HttpStatus.CONFLICT, "해당 이메일로 생성된 팀이 이미 존재합니다."),
  DUPLICATE_DATA(HttpStatus.CONFLICT, "해당하는 데이터가 이미 존재합니다");

  private final HttpStatus httpStatus;
  private final String message;
}
