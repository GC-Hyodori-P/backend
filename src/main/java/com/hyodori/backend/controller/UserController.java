package com.hyodori.backend.controller;

import com.hyodori.backend.dto.*;
import com.hyodori.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        Long userId = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SignupResponseDto(userId, "회원가입이 완료되었습니다." )
        );
    }

    @GetMapping("/check-duplicate")
    public ResponseEntity<Map<String, Boolean>> checkPhoneNumberDuplicate(@RequestParam String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("전화번호는 필수 입력값입니다.");
        }

        boolean isDuplicate = userService.isPhoneNumberDuplicate(phone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/jwt")
    public ResponseEntity<TokenResponseDto> reissueToken(@RequestHeader("Authorization") String bearerToken) {
        if (!bearerToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("올바르지 않은 Authorization 헤더입니다.");
        }

        String refreshToken = bearerToken.substring(7); // "Bearer " 이후 토큰 추출
        TokenResponseDto tokenResponse = userService.reissueTokens(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin(@RequestBody @Valid SigninRequestDto requestDto) {
        SigninResponseDto responseDto = userService.signin(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
