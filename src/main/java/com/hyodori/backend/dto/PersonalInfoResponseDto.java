package com.hyodori.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PersonalInfoResponseDto {

    private Long personalInfoId;
    private String wakeupTime;
    private String sleepTime;
    private String breakfastTime;
    private String lunchTime;
    private String dinnerTime;
    private String medicineTime;
}