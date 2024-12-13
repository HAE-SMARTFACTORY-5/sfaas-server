package com.hae5.sfaas.auth.service;

import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.auth.dto.response.RegisterResponse;
import com.hae5.sfaas.user.mapper.UserMapper;
import com.hae5.sfaas.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;

    private final LocalLoginService localLoginService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        return localLoginService.login(request);
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        User user = User.create(request, passwordEncoder.encode(request.password()));
        userMapper.save(user);
        return RegisterResponse.from(user.getUserId());
    }

}
