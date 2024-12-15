package com.hae5.sfaas.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    /* 예시 Basic (-0000) */
    BASIC_NOT_FOUND_ERROR("-0000", HttpStatus.BAD_REQUEST, "존재하지 않는 Basic"),

    /* 시스템 에러 (0000~) */
    INTERNAL_SERVER_ERROR("0000", HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버에러"),
    REQUEST_BODY_NOT_FOUND_ERROR("0001", HttpStatus.BAD_REQUEST, "요청의 Body가 없음"),
    REQUEST_ARGUMENT_NOT_VALID_ERROR("0002", HttpStatus.BAD_REQUEST, "요청에 필요한 필드가 없음 -> "),
    DATABASE_ERROR("0003", HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러"),
    API_NOT_FOUND_ERROR("0004", HttpStatus.BAD_REQUEST, "존재하지 않는 API"),
    INVALIDATE_ENUM_ERROR("0005", HttpStatus.BAD_REQUEST, "잘못된 Enum -> "),
    TOKEN_EXPIRED_ERROR("0005", HttpStatus.BAD_REQUEST, "만료된 토큰"),
    TOKEN_INVALIDATE_ERROR("0006", HttpStatus.BAD_REQUEST, "잘못된 토큰"),
    TOKEN_NOT_CONTAIN_ERROR("0007", HttpStatus.BAD_REQUEST, "토큰이 없음"),
    NO_AUTHORITY_USER_ERROR("0008", HttpStatus.FORBIDDEN, "접근 권한 없음"),

    /* User 에러 (1000~) */
    USER_NOT_FOUNT_ERROR("1000", HttpStatus.BAD_REQUEST, "존재하지 않는 사용자"),
    DUPLICATE_EMPLOYEE_ID_ERROR("1001", HttpStatus.BAD_REQUEST, "사원번호 중복"),
    USER_PASSWORD_NOT_MATCH_ERROR("1002", HttpStatus.BAD_REQUEST, "비밀번호 불일치"),
    ADMIN_CAN_NOT_DELETE_ERROR("1003", HttpStatus.BAD_REQUEST, "관리자 삭제 불가"),

    /* Major Schedule 에러 (2000~)  */
    SCHDUEL_NOT_FOUND_ERROR("2000", HttpStatus.BAD_REQUEST, "존재하지 않는 Maintenance Schedule");


    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

}
