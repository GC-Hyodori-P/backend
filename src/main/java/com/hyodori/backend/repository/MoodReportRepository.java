package com.hyodori.backend.repository;

import com.hyodori.backend.domain.MoodReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MoodReportRepository extends JpaRepository<MoodReport, Long> {
    List<MoodReport> findByUserId(Long userId);
    List<MoodReport> findByUserIdOrderByCreatedAtDesc(Long userId);
} 