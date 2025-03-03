package com.hae5.sfaas.common.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.response.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (SfaasException exception) {
            setErrorResponse(response, exception);
        }

    }

    private void setErrorResponse(HttpServletResponse response, SfaasException exception) throws IOException {
        log.error("Code : {}, Message : {}", exception.getErrorCode(), exception.getMessage());
        String errorResponse = objectMapper.writeValueAsString(
                ErrorResponse.create(exception.getErrorCode(), exception.getMessage()));
        response.setStatus(exception.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(errorResponse);
    }
}
