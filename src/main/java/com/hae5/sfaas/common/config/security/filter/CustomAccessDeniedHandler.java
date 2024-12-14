package com.hae5.sfaas.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.common.exception.ExceptionCode;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        SfaasException exception = SfaasException.create(ExceptionCode.NO_AUTHORITY_USER_ERROR);
        log.error("Code : {}, Message : {}", exception.getErrorCode(), exception.getMessage());
        String errorResponse = objectMapper.writeValueAsString(
                ErrorResponse.create(exception.getErrorCode(), exception.getMessage()));
        response.setStatus(exception.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(errorResponse);
    }
}
