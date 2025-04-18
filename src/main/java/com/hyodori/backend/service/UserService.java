package com.hyodori.backend.service;

import com.hyodori.backend.domain.User;
import com.hyodori.backend.dto.SigninRequestDto;
import com.hyodori.backend.dto.SigninResponseDto;
import com.hyodori.backend.dto.SignupRequestDto;
import com.hyodori.backend.dto.TokenResponseDto;
import com.hyodori.backend.exception.PhoneNumberDuplicateException;
import com.hyodori.backend.jwt.JwtUtil;
import com.hyodori.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Long signup(SignupRequestDto requestDto) {
        // 전화번호 중복 확인
        if(userRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new PhoneNumberDuplicateException("이미 가입된 전화번호입니다.");
        }

        // 비밀번호 일치 확인
        if(!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 암호화 후 유저 생성
        User user = User.builder()
                .name(requestDto.getName())
                .phoneNumber(requestDto.getPhoneNumber())
                .password((passwordEncoder.encode(requestDto.getPassword())))
                .build();

        return userRepository.save(user).getUserId();
    }

    public boolean isPhoneNumberDuplicate(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public TokenResponseDto reissueTokens(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token이 유효하지 않거나 존재하지 않습니다.");
        }

        String phone = jwtUtil.getSubject(refreshToken);
        Optional<User> userOptional = userRepository.findByPhoneNumber(phone);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }

        String newAccessToken = jwtUtil.createAccessToken(phone);
        String newRefreshToken = jwtUtil.createRefreshToken(phone);

        return new TokenResponseDto(newAccessToken, newRefreshToken);
    }

    public SigninResponseDto signin(SigninRequestDto requestDto){
        User user = userRepository.findByPhoneNumber(requestDto.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 전화번호입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(user.getPhoneNumber());
        String refreshToken = jwtUtil.createRefreshToken(user.getPhoneNumber());

        return new SigninResponseDto(accessToken, refreshToken);
    }
}
