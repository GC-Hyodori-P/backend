package com.hyodori.backend.service;

import com.hyodori.backend.domain.MoodReport;
import com.hyodori.backend.domain.User;
import com.hyodori.backend.dto.MoodReportDTO;
import com.hyodori.backend.repository.MoodReportRepository;
import com.hyodori.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodReportService {

    @Autowired
    private MoodReportRepository moodReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public MoodReportDTO createMoodReport(MoodReportDTO moodReportDTO) {
        User user = userRepository.findById(moodReportDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MoodReport moodReport = new MoodReport();
        moodReport.setUser(user);
        moodReport.setTitle(moodReportDTO.getTitle());
        moodReport.setContent(moodReportDTO.getContent());
        moodReport.setMoodType(moodReportDTO.getMoodType());
        moodReport.setMoodScore(moodReportDTO.getMoodScore());

        MoodReport savedReport = moodReportRepository.save(moodReport);
        return convertToDTO(savedReport);
    }

    @Transactional(readOnly = true)
    public List<MoodReportDTO> getUserMoodReports(Long userId) {
        return moodReportRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MoodReportDTO getMoodReport(Long id) {
        MoodReport moodReport = moodReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mood report not found"));
        return convertToDTO(moodReport);
    }

    @Transactional
    public MoodReportDTO updateMoodReport(Long id, MoodReportDTO moodReportDTO) {
        MoodReport moodReport = moodReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mood report not found"));

        moodReport.setTitle(moodReportDTO.getTitle());
        moodReport.setContent(moodReportDTO.getContent());
        moodReport.setMoodType(moodReportDTO.getMoodType());
        moodReport.setMoodScore(moodReportDTO.getMoodScore());

        MoodReport updatedReport = moodReportRepository.save(moodReport);
        return convertToDTO(updatedReport);
    }

    @Transactional
    public void deleteMoodReport(Long id) {
        moodReportRepository.deleteById(id);
    }

    private MoodReportDTO convertToDTO(MoodReport moodReport) {
        MoodReportDTO dto = new MoodReportDTO();
        dto.setId(moodReport.getId());
        dto.setTitle(moodReport.getTitle());
        dto.setContent(moodReport.getContent());
        dto.setMoodType(moodReport.getMoodType());
        dto.setMoodScore(moodReport.getMoodScore());
        dto.setCreatedAt(moodReport.getCreatedAt());
        dto.setUpdatedAt(moodReport.getUpdatedAt());
        dto.setUserId(moodReport.getUser().getId());
        return dto;
    }
} 