package com.hyodori.backend.controller;

import com.hyodori.backend.dto.InterestResponseDto;
import com.hyodori.backend.service.UserInterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-interests")
@RequiredArgsConstructor
public class UserInterestController {

    private final UserInterestService userInterestService;

    @PostMapping
    public ResponseEntity<Map<String, String>> addUserInterests(
            @AuthenticationPrincipal String phoneNumber,
            @RequestBody List<Long> interestIds
    ) {
        userInterestService.addUserInterests(phoneNumber, interestIds);
        Map<String, String> response = Map.of("message", "관심사가 성공적으로 등록되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateUserInterests(
            @AuthenticationPrincipal String phoneNumber,
            @RequestBody List<Long> interestIds
    ) {
        userInterestService.addUserInterests(phoneNumber, interestIds);
        Map<String, String> response = Map.of("message", "관심사가 성공적으로 수정되었습니다.");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InterestResponseDto>> getUserInterests(
            @AuthenticationPrincipal String phoneNumber
    ) {
        List<InterestResponseDto> response = userInterestService.getUserInterests(phoneNumber);
        return ResponseEntity.ok(response);
    }
}