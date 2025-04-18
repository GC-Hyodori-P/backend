package com.hyodori.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_gds_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGdsResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gds_id", nullable = false)
    private GdsQuestion gdsQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private GdsResponseValue response;

    private LocalDate responseAt;

    private int cycle;
}
