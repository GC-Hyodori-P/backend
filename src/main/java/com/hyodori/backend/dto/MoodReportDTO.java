package com.hyodori.backend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MoodReportDTO {
    private Long id;
    private String title;
    private String content;
    private String moodType;
    private Integer moodScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
} 