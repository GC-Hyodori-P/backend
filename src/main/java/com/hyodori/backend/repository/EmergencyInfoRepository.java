package com.hyodori.backend.repository;

import com.hyodori.backend.domain.EmergencyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyInfoRepository  extends JpaRepository<EmergencyInfo, Long> {
    EmergencyInfo findByUser_UserId(Long userId);
}
