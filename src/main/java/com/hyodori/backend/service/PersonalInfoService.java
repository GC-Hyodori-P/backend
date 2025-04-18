package com.hyodori.backend.service;

import com.hyodori.backend.domain.PersonalInfo;
import com.hyodori.backend.domain.User;
import com.hyodori.backend.dto.PersonalInfoRequestDto;
import com.hyodori.backend.dto.PersonalInfoResponseDto;
import com.hyodori.backend.repository.PersonalInfoRepository;
import com.hyodori.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final UserRepository userRepository;

    @Transactional
    public PersonalInfoResponseDto createPersonalInfoByPhoneNumber(String phoneNumber, PersonalInfoRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        if (personalInfoRepository.findByUser(user).isPresent()) {
            throw new IllegalStateException("이미 개인정보가 등록되어 있습니다.");
        }

        PersonalInfo personalInfo = PersonalInfo.builder()
                .user(user)
                .wakeupTime(LocalTime.parse(requestDto.getWakeupTime()))
                .sleepTime(LocalTime.parse(requestDto.getSleepTime()))
                .breakfastTime(LocalTime.parse(requestDto.getBreakfastTime()))
                .lunchTime(LocalTime.parse(requestDto.getLunchTime()))
                .dinnerTime(LocalTime.parse(requestDto.getDinnerTime()))
                .medicineTime(LocalTime.parse(requestDto.getMedicineTime()))
                .build();

        PersonalInfo saved = personalInfoRepository.save(personalInfo);

        return convertToDto(saved);
    }

    @Transactional
    public PersonalInfoResponseDto updatePersonalInfoByPhoneNumber(String phoneNumber, PersonalInfoRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        PersonalInfo personalInfo = personalInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("등록된 개인정보가 없습니다."));

        personalInfo.setWakeupTime(LocalTime.parse(requestDto.getWakeupTime()));
        personalInfo.setSleepTime(LocalTime.parse(requestDto.getSleepTime()));
        personalInfo.setBreakfastTime(LocalTime.parse(requestDto.getBreakfastTime()));
        personalInfo.setLunchTime(LocalTime.parse(requestDto.getLunchTime()));
        personalInfo.setDinnerTime(LocalTime.parse(requestDto.getDinnerTime()));
        personalInfo.setMedicineTime(LocalTime.parse(requestDto.getMedicineTime()));

        return convertToDto(personalInfo);
    }

    @Transactional(readOnly = true)
    public PersonalInfoResponseDto getPersonalInfoByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        PersonalInfo personalInfo = personalInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("등록된 개인정보가 없습니다."));

        return convertToDto(personalInfo);
    }


    private PersonalInfoResponseDto convertToDto(PersonalInfo entity) {
        return PersonalInfoResponseDto.builder()
                .personalInfoId(entity.getPersonalInfoId())
                .wakeupTime(entity.getWakeupTime().toString())
                .sleepTime(entity.getSleepTime().toString())
                .breakfastTime(entity.getBreakfastTime().toString())
                .lunchTime(entity.getLunchTime().toString())
                .dinnerTime(entity.getDinnerTime().toString())
                .medicineTime(entity.getMedicineTime().toString())
                .build();
    }
}