package com.hyodori.backend.controller;

import com.hyodori.backend.dto.PersonalInfoRequestDto;
import com.hyodori.backend.dto.PersonalInfoResponseDto;
import com.hyodori.backend.service.PersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-info")
@RequiredArgsConstructor
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;

    @PostMapping
    public ResponseEntity<PersonalInfoResponseDto> createPersonalInfo(
            @AuthenticationPrincipal String phoneNumber,
            @RequestBody @Valid PersonalInfoRequestDto requestDto
    ) {
        PersonalInfoResponseDto response = personalInfoService.createPersonalInfoByPhoneNumber(phoneNumber, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<PersonalInfoResponseDto> updatePersonalInfo(
            @AuthenticationPrincipal String phoneNumber,
            @RequestBody @Valid PersonalInfoRequestDto requestDto
    ) {
        PersonalInfoResponseDto response = personalInfoService.updatePersonalInfoByPhoneNumber(phoneNumber, requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PersonalInfoResponseDto> getPersonalInfo(
            @AuthenticationPrincipal String phoneNumber
    ) {
        PersonalInfoResponseDto response = personalInfoService.getPersonalInfoByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(response);
    }
}