package com.hyodori.backend.repository;

import com.hyodori.backend.domain.EmergencyInfo;
import com.hyodori.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmergencyInfoRepository  extends JpaRepository<EmergencyInfo, Long> {
    Optional<EmergencyInfo> findByUser(User user);
}
