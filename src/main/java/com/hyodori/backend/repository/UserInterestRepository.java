package com.hyodori.backend.repository;

import com.hyodori.backend.domain.User;
import com.hyodori.backend.domain.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    void deleteByUser(User user);
    List<UserInterest> findByUser(User user);
}