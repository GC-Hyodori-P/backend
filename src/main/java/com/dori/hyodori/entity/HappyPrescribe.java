package com.dori.hyodori.entity;

import com.dori.hyodori.enumeration.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "happy_prescribe")
public class HappyPrescribe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    @Column(nullable = false)
    private String status; // 현재 상태에 대한 설명

    @Column(nullable = false)
    private String prescribe; // 행복 처방
}
