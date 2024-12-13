package com.hae5.sfaas.auth.controller;

import com.hae5.sfaas.auth.dto.request.LoginRequest;
import com.hae5.sfaas.auth.dto.request.RegisterRequest;
import com.hae5.sfaas.auth.dto.response.LoginResponse;
import com.hae5.sfaas.auth.dto.response.RegisterResponse;
import com.hae5.sfaas.auth.service.AuthService;
import com.hae5.sfaas.auth.service.LocalLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok().body(response);
    }

}
