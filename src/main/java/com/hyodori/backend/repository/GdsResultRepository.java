package com.hyodori.backend.repository;

import com.hyodori.backend.domain.GdsResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GdsResultRepository extends JpaRepository<GdsResult, Long> {
    List<GdsResult> findByUserIdOrderByCreatedAtDesc(Long userId);
} 