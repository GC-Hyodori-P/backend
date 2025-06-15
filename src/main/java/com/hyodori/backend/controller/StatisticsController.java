package com.hyodori.backend.controller;

import com.hyodori.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserStatistics(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.getUserStatistics(userId));
    }

    @GetMapping("/user/{userId}/emotion-trends")
    public ResponseEntity<Map<String, Object>> getEmotionTrends(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(statisticsService.getEmotionTrends(userId, startDate, endDate));
    }
} 