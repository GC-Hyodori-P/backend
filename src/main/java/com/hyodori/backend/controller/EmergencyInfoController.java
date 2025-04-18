package com.hyodori.backend.controller;

import com.hyodori.backend.dto.EmergencyInfoRequestDto;
import com.hyodori.backend.dto.EmergencyInfoResponseDto;
import com.hyodori.backend.service.EmergencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emergency-info")
@RequiredArgsConstructor
public class EmergencyInfoController {

    private final EmergencyInfoService emergencyInfoService;

    @PostMapping
    public ResponseEntity<EmergencyInfoResponseDto> createEmergencyInfo(
            @AuthenticationPrincipal String phoneNumber,
            @RequestBody EmergencyInfoRequestDto requestDto
    ) {
        EmergencyInfoResponseDto response = emergencyInfoService.createEmergencyInfoByPhoneNumber(phoneNumber, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
