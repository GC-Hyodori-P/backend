package com.hyodori.backend.dto;

import com.hyodori.backend.domain.BloodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmergencyInfoResponseDto {
    private Long emergencyInfoId;
    private BloodType bloodType;
    private String medications;
    private String medicalHistory;
    private String address;
    private String emergencyContactName;
    private String emergencyContactPhone;
}
