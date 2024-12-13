package com.hae5.sfaas.auth.service;

import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.common.exception.SfaasException;
import com.hae5.sfaas.common.jwt.JwtProvider;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hae5.sfaas.common.exception.ExceptionCode.USER_NOT_FOUNT_ERROR;
import static com.hae5.sfaas.common.exception.ExceptionCode.USER_PASSWORD_NOT_MATCH_ERROR;

@Service
@RequiredArgsConstructor
public class JwtLocalLoginService implements LocalLoginService {

    private final UserMapper userMapper;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByEmployeeId(request.employeeId())
                        .orElseThrow(() -> SfaasException.create(USER_NOT_FOUNT_ERROR));
        checkPasswordMatch(request.password(), user.getPassword());
        String accessToken = jwtProvider.createAccessToken(user.getUserId(), user.getRole());
        return LoginResponse.from(accessToken);
    }

    private void checkPasswordMatch(String userPassword, String requestPassword) {
        if (passwordEncoder.matches(requestPassword, userPassword)) {
            throw SfaasException.create(USER_PASSWORD_NOT_MATCH_ERROR);
        }
    }

}
