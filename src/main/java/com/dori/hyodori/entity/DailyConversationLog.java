package com.dori.hyodori.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "daily_conversation_log")
public class DailyConversationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate conversationDate; //대화가 발생한 날짜

    @Column(nullable = false)
    private int gdsCount = 0; //GDS 질문에 대한 대화 횟수

    @Column(nullable = false)
    private int dailyCheckCount = 0; // 일상 챙김 대화 횟수

    @Column(nullable = false)
    private boolean missionReminder = false; // 미션 상기 여부

    @Column(nullable = false)
    private boolean isCompleted = false; // 대화 종료 여부

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
