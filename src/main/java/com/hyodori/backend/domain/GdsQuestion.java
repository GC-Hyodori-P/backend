package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "gds_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GdsQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalQuestion;

    @Column(columnDefinition = "TEXT")
    private String friendlyQuestion;

    @Column(nullable = false)
    private Integer questionNumber;

    @Column(nullable = false)
    private Boolean isPositive; // 긍정적 응답이 높은 점수를 받는지 여부

    @CreationTimestamp
    private LocalDateTime createdAt;
}
