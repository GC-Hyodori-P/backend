package com.hyodori.backend.repository;

import com.hyodori.backend.domain.PersonalInfo;
import com.hyodori.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    Optional<PersonalInfo> findByUser(User user);
}