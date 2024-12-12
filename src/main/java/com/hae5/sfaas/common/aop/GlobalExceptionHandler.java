package com.hae5.sfaas.common.aop;

import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

import static com.hae5.sfaas.common.exception.ExceptionCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_TEMPLATE = "Error: {}, Class : {}, Code : {}, Message : {}";

    @ExceptionHandler(SfaasException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(SfaasException exception) {
        log.error(LOG_TEMPLATE,
                "ApplicationException",
                exception.getClass().getSimpleName(),
                exception.getErrorCode(),
                exception.getMessage()
        );
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorResponse.create(exception.getErrorCode(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error(LOG_TEMPLATE,
                "MethodArgumentTypeMismatchException",
                exception.getClass().getSimpleName(),
                REQUEST_BODY_NOT_FOUND_ERROR.getCode(),
                exception.getMessage()
        );
        String enumInput = exception.getMessage().split("for value \\[")[1].split("]")[0];
        return ResponseEntity.status(INVALIDATE_ENUM_ERROR.getHttpStatus())
                .body(ErrorResponse.create(INVALIDATE_ENUM_ERROR.getCode(), INVALIDATE_ENUM_ERROR.getMessage() + enumInput));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNoValidArgumentException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        List<String> errorMessages = bindingResult.getFieldErrors().stream()
                .map(error -> "Field: " + error.getField() + ", Error: " + error.getDefaultMessage()).toList();

        String arguments = String.join(", ", errorMessages);
        log.error(LOG_TEMPLATE,
                "MethodArgumentNotValidException",
                exception.getClass().getSimpleName(),
                REQUEST_ARGUMENT_NOT_VALID_ERROR.getCode() + arguments,
                exception.getMessage()
        );
        return ResponseEntity.status(REQUEST_ARGUMENT_NOT_VALID_ERROR.getHttpStatus())
                .body(ErrorResponse.create(REQUEST_ARGUMENT_NOT_VALID_ERROR.getCode(),
                        REQUEST_ARGUMENT_NOT_VALID_ERROR.getMessage() + arguments));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNoBodyException(HttpMessageNotReadableException exception) {
        log.error(LOG_TEMPLATE,
                "HttpMessageNotReadableException",
                exception.getClass().getSimpleName(),
                REQUEST_BODY_NOT_FOUND_ERROR.getCode(),
                exception.getMessage()
        );
        if (exception.getMessage().contains("Enum")) {
            String enumInput = exception.getMessage().split("\"")[1];
            return ResponseEntity.status(INVALIDATE_ENUM_ERROR.getHttpStatus())
                    .body(ErrorResponse.create(INVALIDATE_ENUM_ERROR.getCode(), INVALIDATE_ENUM_ERROR.getMessage() + enumInput));
        }
        return ResponseEntity.status(REQUEST_BODY_NOT_FOUND_ERROR.getHttpStatus())
                .body(ErrorResponse.create(REQUEST_BODY_NOT_FOUND_ERROR.getCode(), REQUEST_BODY_NOT_FOUND_ERROR.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException exception) {
        log.error(LOG_TEMPLATE,
                "NoResourceFoundException",
                exception.getClass().getSimpleName(),
                API_NOT_FOUND_ERROR.getCode(),
                exception.getMessage()
        );
        return ResponseEntity.status(API_NOT_FOUND_ERROR.getHttpStatus())
                .body(ErrorResponse.create(API_NOT_FOUND_ERROR.getCode(), API_NOT_FOUND_ERROR.getMessage()));
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ErrorResponse> handleBadSqlGrammarException(BadSqlGrammarException exception) {
        log.error(LOG_TEMPLATE,
                "BadSqlGrammarException",
                exception.getClass().getSimpleName(),
                DATABASE_ERROR.getCode(),
                exception.getMessage()
        );
        return ResponseEntity.status(DATABASE_ERROR.getHttpStatus())
                .body(ErrorResponse.create(DATABASE_ERROR.getCode(), DATABASE_ERROR.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        log.error(LOG_TEMPLATE,
                "RuntimeException",
                exception.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR.getCode(),
                exception.getMessage()
        );
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.create(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(LOG_TEMPLATE,
                "Exception",
                exception.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR.getCode(),
                exception.getMessage()
        );
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.create(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage()));
    }

}
