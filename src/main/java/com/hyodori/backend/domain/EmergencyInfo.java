package com.hyodori.backend.domain;

import com.hyodori.backend.dto.EmergencyInfoRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emergencyInfoId;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Column(columnDefinition = "TEXT")
    private String medications;

    @Column(columnDefinition = "TEXT")
    private String medicalHistory;;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String emergencyContactName;

    @Column(length = 15)
    private String emergencyContactPhone;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public void update(EmergencyInfoRequestDto dto) {
        this.bloodType = dto.getBloodType();
        this.medications = dto.getMedications();
        this.medicalHistory = dto.getMedicalHistory();
        this.address = dto.getAddress();
        this.emergencyContactName = dto.getEmergencyContactName();
        this.emergencyContactPhone = dto.getEmergencyContactPhone();
    }
}
