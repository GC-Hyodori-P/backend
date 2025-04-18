package com.hyodori.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 항목입니다.")
    @Pattern(regexp = "^010\\d{7,8}$", message = "전화번호는 010으로 시작하는 10~11자리 숫자여야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Pattern(regexp = "^\\d{4}$", message = "비밀번호는 4자리 숫자여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 항목입니다.")
    private String passwordConfirm;

}
