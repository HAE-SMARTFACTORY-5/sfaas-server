package com.hae5.sfaas.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SfaasException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    private SfaasException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.errorCode = exceptionCode.getCode();
        this.httpStatus = exceptionCode.getHttpStatus();
    }

    public static SfaasException create(ExceptionCode exceptionCode) {
        return new SfaasException(exceptionCode);
    }

}