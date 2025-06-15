package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "emotion_analyses")
public class EmotionAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String emotionType; // HAPPY, SAD, ANGRY, CALM, etc.

    @Column(nullable = false)
    private Double emotionScore; // 0.0 ~ 1.0

    @Column(columnDefinition = "TEXT")
    private String analysis; // GPT를 통한 감정 분석 결과

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
} 