package com.dori.hyodori.entity;

import com.dori.hyodori.enumeration.ResponseEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "gds_responses")
public class GdsResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private GdsQuestions question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResponseEnum response;

    @Column(nullable = false)
    private LocalDate responseDate;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


}
