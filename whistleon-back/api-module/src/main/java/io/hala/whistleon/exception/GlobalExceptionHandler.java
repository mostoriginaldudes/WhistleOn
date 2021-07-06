package io.hala.whistleon.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {MethodArgumentNotValidException.class, IllegalArgumentException.class})
  public ResponseEntity<ExceptionResponse> handleValidException() {
    return ExceptionResponse.toResponseEntity(ExceptionCode.INVALID_FORM_DATA);
  }

  @ExceptionHandler(value = {CustomException.class})
  public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
    return ExceptionResponse.toResponseEntity(e.getExceptionCode());
  }

}
