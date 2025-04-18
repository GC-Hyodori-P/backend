package com.hyodori.backend.repository;

import com.hyodori.backend.domain.MindReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MindReportRepository extends JpaRepository<MindReport, Long> {
    List<MindReport> findAllByUser_UserId(Long userId);
    MindReport findByUser_UserIdAndReportWeek(Long userId, LocalDate reportWeek);
}
