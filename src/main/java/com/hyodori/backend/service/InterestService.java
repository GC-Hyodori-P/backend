// service/InterestService.java
package com.hyodori.backend.service;

import com.hyodori.backend.domain.Interest;
import com.hyodori.backend.dto.InterestResponseDto;
import com.hyodori.backend.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;

    public List<InterestResponseDto> getAllInterests() {
        return interestRepository.findAll().stream()
                .map(interest -> new InterestResponseDto(interest.getInterestId(), interest.getInterestName()))
                .collect(Collectors.toList());
    }
}