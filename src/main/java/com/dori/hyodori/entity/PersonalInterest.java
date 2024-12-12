package com.dori.hyodori.entity;

import com.dori.hyodori.enumeration.InterestEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "personal_interest")
public class PersonalInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestEnum interest;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
