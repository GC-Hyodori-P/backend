package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "gds_questsions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GdsQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gdsId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    private GdsCategory category;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
