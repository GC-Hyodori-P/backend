package com.hyodori.backend.service;

import com.hyodori.backend.domain.Interest;
import com.hyodori.backend.domain.User;
import com.hyodori.backend.domain.UserInterest;
import com.hyodori.backend.dto.InterestResponseDto;
import com.hyodori.backend.repository.InterestRepository;
import com.hyodori.backend.repository.UserInterestRepository;
import com.hyodori.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInterestService {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final UserInterestRepository userInterestRepository;

    @Transactional
    public void addUserInterests(String phoneNumber, List<Long> interestIds) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 기존 관심사 삭제
        userInterestRepository.deleteByUser(user);

        // 새 관심사 저장
        List<Interest> interests = interestRepository.findAllById(interestIds);
        List<UserInterest> userInterests = interests.stream()
                .map(interest -> UserInterest.builder()
                        .user(user)
                        .interest(interest)
                        .build())
                .toList();

        userInterestRepository.saveAll(userInterests);
    }

    @Transactional(readOnly = true)
    public List<InterestResponseDto> getUserInterests(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<UserInterest> userInterests = userInterestRepository.findByUser(user);

        return userInterests.stream()
                .map(ui -> new InterestResponseDto(ui.getInterest().getInterestId(), ui.getInterest().getInterestName()))
                .toList();
    }
}