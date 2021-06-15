package io.hala.whistleon.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

  private final ExceptionCode exceptionCode;
}
