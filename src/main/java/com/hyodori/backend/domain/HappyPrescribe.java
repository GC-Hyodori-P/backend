package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "happy_prescribe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HappyPrescribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescribeId;

    @Column(columnDefinition = "TEXT")
    private String status;

    @Column(columnDefinition = "TEXT")
    private String prescribe;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false, unique = true)
    private MindReport report;
}
