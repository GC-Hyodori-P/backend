package com.dori.hyodori.entity;

import com.dori.hyodori.enumeration.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "missions")
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String missionText;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
