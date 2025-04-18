package com.hyodori.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SigninRequestDto {
    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}
