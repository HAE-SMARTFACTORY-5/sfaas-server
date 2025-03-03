package com.hae5.sfaas.common.config.security.filter;

import com.hae5.sfaas.common.exception.SfaasException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import static com.hae5.sfaas.common.exception.ExceptionCode.TOKEN_NOT_CONTAIN_ERROR;


@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        if (request.getHeader("Authorization") == null) {
            throw SfaasException.create(TOKEN_NOT_CONTAIN_ERROR);
        }
    }
}
