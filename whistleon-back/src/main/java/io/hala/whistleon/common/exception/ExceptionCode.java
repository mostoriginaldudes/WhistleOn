package io.hala.whistleon.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    /**
     * Todo 익셉션코드 구체화해서 더 적어야됨
     */

    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "계정 정보 오류"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류");


    private final HttpStatus httpStatus;
    private final String message;
}
