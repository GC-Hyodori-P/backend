package com.hyodori.backend.repository;

import com.hyodori.backend.domain.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findAllByUser_UserId(Long userId);
    List<UserMission> findAllByUser_UserIdAndIsDoneFalse(Long userId);
}
