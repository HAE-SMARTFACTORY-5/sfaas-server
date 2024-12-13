package com.hae5.sfaas.auth.service;

import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;

public interface LocalLoginService {
    LoginResponse login(LoginRequest request);
}
