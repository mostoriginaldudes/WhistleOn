package io.hala.whistleon.common.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("계정이 유효하지 않습니다.");
    }
}
