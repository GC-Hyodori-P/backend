package com.hyodori.backend.dto;

import com.hyodori.backend.domain.BloodType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EmergencyInfoRequestDto {
    @NotNull(message = "혈액형은 필수 입력입니다.")
    private BloodType bloodType;

    @NotBlank(message = "복용약물은 필수 입력입니다.")
    private String medications;

    @NotBlank(message = "지병/병력은 필수 입력입니다.")
    private String medicalHistory;

    @NotBlank(message = "자택 주소는 필수 입력입니다.")
    private String address;

    @NotBlank(message = "긴급 연락자 이름은 필수 입력입니다.")
    private String emergencyContactName;

    @NotBlank(message = "긴급 연락처는 필수 입력입니다.")
    private String emergencyContactPhone;
}
