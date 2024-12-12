package com.dori.hyodori.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "mind_report")
public class MindReport {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate reportWeek; // 주차의 시작 날짜(예: 월요일)

    @Column(nullable = false)
    private int totalScore;

    @Column(nullable = false)
    private int satisfaction;

    @Column(nullable = false)
    private int interest;

    @Column(nullable = false)
    private int positivity;

    @Column(nullable = false)
    private int emotion;

    @Column(nullable = false)
    private int selfEsteem;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
