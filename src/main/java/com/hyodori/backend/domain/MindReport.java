package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "mind_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MindReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate reportWeek;

    private int totalScore;
    private int satisfaction;
    private int interest;
    private int positivity;
    private int emotion;
    private int selfEsteem;
    private int health;

    @CreationTimestamp
    private LocalDate createdAt;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private HappyPrescribe happyPrescribe;
}
