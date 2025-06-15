package com.hyodori.backend.controller;

import com.hyodori.backend.dto.MoodReportDTO;
import com.hyodori.backend.service.MoodReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood-reports")
public class MoodReportController {

    @Autowired
    private MoodReportService moodReportService;

    @PostMapping
    public ResponseEntity<MoodReportDTO> createMoodReport(@RequestBody MoodReportDTO moodReportDTO) {
        return ResponseEntity.ok(moodReportService.createMoodReport(moodReportDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MoodReportDTO>> getUserMoodReports(@PathVariable Long userId) {
        return ResponseEntity.ok(moodReportService.getUserMoodReports(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoodReportDTO> getMoodReport(@PathVariable Long id) {
        return ResponseEntity.ok(moodReportService.getMoodReport(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoodReportDTO> updateMoodReport(
            @PathVariable Long id,
            @RequestBody MoodReportDTO moodReportDTO) {
        return ResponseEntity.ok(moodReportService.updateMoodReport(id, moodReportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoodReport(@PathVariable Long id) {
        moodReportService.deleteMoodReport(id);
        return ResponseEntity.ok().build();
    }
} 