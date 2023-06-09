package com.multi.semo.auth.controller;

import com.multi.semo.auth.dto.request.LoginRequest;
import com.multi.semo.auth.dto.request.RefreshTokenRequest;
import com.multi.semo.auth.dto.response.TokenResponse;
import com.multi.semo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody RefreshTokenRequest request) {
        TokenResponse response = authService.reissue(request);
        return ResponseEntity.ok(response);
    }
}
