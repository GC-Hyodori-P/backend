package com.hyodori.backend.service;

import com.hyodori.backend.domain.EmergencyInfo;
import com.hyodori.backend.domain.User;
import com.hyodori.backend.dto.EmergencyInfoRequestDto;
import com.hyodori.backend.dto.EmergencyInfoResponseDto;
import com.hyodori.backend.repository.EmergencyInfoRepository;
import com.hyodori.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmergencyInfoService {

    private final EmergencyInfoRepository emergencyInfoRepository;
    private final UserRepository userRepository;

    @Transactional
    public EmergencyInfoResponseDto createEmergencyInfoByPhoneNumber(String phoneNumber, EmergencyInfoRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        if (emergencyInfoRepository.findByUser(user).isPresent()) {
            throw new IllegalStateException("이미 긴급대응정보가 등록되어 있습니다.");
        }

        EmergencyInfo emergencyInfo = EmergencyInfo.builder()
                .user(user)
                .bloodType(requestDto.getBloodType())
                .medications(requestDto.getMedications())
                .medicalHistory(requestDto.getMedicalHistory())
                .address(requestDto.getAddress())
                .emergencyContactName(requestDto.getEmergencyContactName())
                .emergencyContactPhone(requestDto.getEmergencyContactPhone())
                .build();

        EmergencyInfo saved = emergencyInfoRepository.save(emergencyInfo);
        return convertToDto(saved);
    }

    @Transactional
    public EmergencyInfoResponseDto updateEmergencyInfoByPhoneNumber(String phoneNumber, EmergencyInfoRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        EmergencyInfo emergencyInfo = emergencyInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("등록된 긴급대응정보가 없습니다."));

        emergencyInfo.update(requestDto);
        return convertToDto(emergencyInfo);
    }
    @Transactional(readOnly = true)
    public EmergencyInfoResponseDto getEmergencyInfo(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        EmergencyInfo emergencyInfo = emergencyInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("등록된 긴급대응정보가 없습니다."));

        return convertToDto(emergencyInfo);
    }

    private EmergencyInfoResponseDto convertToDto(EmergencyInfo entity) {
        return EmergencyInfoResponseDto.builder()
                .emergencyInfoId(entity.getEmergencyInfoId())
                .bloodType(entity.getBloodType())
                .medications(entity.getMedications())
                .medicalHistory(entity.getMedicalHistory())
                .address(entity.getAddress())
                .emergencyContactName(entity.getEmergencyContactName())
                .emergencyContactPhone(entity.getEmergencyContactPhone())
                .build();
    }


}