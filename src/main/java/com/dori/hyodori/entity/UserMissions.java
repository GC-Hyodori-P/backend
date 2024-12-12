package com.dori.hyodori.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user_missions")
public class UserMissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Missions mission;

    @Column(nullable = false)
    private LocalDate assignDate;

    @Column(nullable = false)
    private boolean isReminded = false;

    @Column(nullable = false)
    private boolean completed = false;

    @Column
    private LocalDateTime completedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
