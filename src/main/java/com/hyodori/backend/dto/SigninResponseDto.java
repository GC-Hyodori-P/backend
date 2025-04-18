package com.hyodori.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResponseDto {
    private String accessToken;
    private String refreshToken;
}
