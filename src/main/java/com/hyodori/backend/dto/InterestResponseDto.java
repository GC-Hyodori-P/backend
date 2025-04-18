// dto/InterestResponseDto.java
package com.hyodori.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterestResponseDto {
    private Long interestId;
    private String interestName;
}