package com.hyodori.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PersonalInfoRequestDto {

    @NotBlank(message = "기상 시간은 필수 입력입니다.")
    private String wakeupTime;

    @NotBlank(message = "취침 시간은 필수 입력입니다.")
    private String sleepTime;

    @NotBlank(message = "아침 식사 시간은 필수 입력입니다.")
    private String breakfastTime;

    @NotBlank(message = "점심 식사 시간은 필수 입력입니다.")
    private String lunchTime;

    @NotBlank(message = "저녁 식사 시간은 필수 입력입니다.")
    private String dinnerTime;

    @NotBlank(message = "복약 시간은 필수 입력입니다.")
    private String medicineTime;
}