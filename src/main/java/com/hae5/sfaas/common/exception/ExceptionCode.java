package com.hae5.sfaas.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    /* 예시 Basic (-0000) */
    BASIC_NOT_FOUND_ERROR("-0000", HttpStatus.BAD_REQUEST, "존재하지 않는 Basic"),

    /* 시스템 에러 (0000~)*/
    INTERNAL_SERVER_ERROR("0000", HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버에러"),
    REQUEST_BODY_NOT_FOUND_ERROR("0001", HttpStatus.BAD_REQUEST, "요청의 Body가 없음"),
    REQUEST_ARGUMENT_NOT_VALID_ERROR("0002", HttpStatus.BAD_REQUEST, "요청에 필요한 필드가 없음 -> "),
    DATABASE_ERROR("0003", HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러"),
    API_NOT_FOUND_ERROR("0004", HttpStatus.BAD_REQUEST, "존재하지 않는 API"),
    INVALIDATE_ENUM_ERROR("0005", HttpStatus.BAD_REQUEST, "잘못된 Enum -> ");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

}
