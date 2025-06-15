package com.hyodori.backend.repository;

import com.hyodori.backend.domain.EmotionAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmotionAnalysisRepository extends JpaRepository<EmotionAnalysis, Long> {
    List<EmotionAnalysis> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<EmotionAnalysis> findByUserIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long userId, LocalDateTime startDate, LocalDateTime endDate);
} 