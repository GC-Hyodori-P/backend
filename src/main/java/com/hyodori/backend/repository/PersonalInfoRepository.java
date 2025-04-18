package com.hyodori.backend.repository;


import com.hyodori.backend.domain.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    PersonalInfo findByUser_UserId(Long userId);
}
