package com.multi.semo.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class RefreshTokenRequest {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
