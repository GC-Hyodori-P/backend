package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(length = 20, nullable = false)
    private String missionName;

    @Enumerated(EnumType.STRING)
    private GdsCategory missionCategory;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
