package com.hyodori.backend.service;

import com.hyodori.backend.domain.*;
import com.hyodori.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private GdsResultRepository gdsResultRepository;

    @Autowired
    private EmotionAnalysisRepository emotionAnalysisRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> getUserStatistics(Long userId) {
        Map<String, Object> statistics = new HashMap<>();

        // GDS 점수 통계
        List<GdsResult> gdsResults = gdsResultRepository.findByUserIdOrderByCreatedAtDesc(userId);
        if (!gdsResults.isEmpty()) {
            statistics.put("latestGdsScore", gdsResults.get(0).getTotalScore());
            statistics.put("gdsScoreHistory", gdsResults.stream()
                    .map(result -> Map.of(
                            "score", result.getTotalScore(),
                            "date", result.getCreatedAt()
                    ))
                    .collect(Collectors.toList()));
        }

        // 감정 분석 통계
        List<EmotionAnalysis> emotionAnalyses = emotionAnalysisRepository.findByUserIdOrderByCreatedAtDesc(userId);
        if (!emotionAnalyses.isEmpty()) {
            Map<String, Double> emotionAverages = emotionAnalyses.stream()
                    .collect(Collectors.groupingBy(
                            EmotionAnalysis::getEmotionType,
                            Collectors.averagingDouble(EmotionAnalysis::getEmotionScore)
                    ));
            statistics.put("emotionAverages", emotionAverages);
        }

        // 대화 통계
        List<Conversation> conversations = conversationRepository.findByUserIdOrderByCreatedAtDesc(userId);
        Map<ConversationType, Long> conversationCounts = conversations.stream()
                .collect(Collectors.groupingBy(
                        Conversation::getType,
                        Collectors.counting()
                ));
        statistics.put("conversationCounts", conversationCounts);

        return statistics;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getEmotionTrends(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        List<EmotionAnalysis> analyses = emotionAnalysisRepository
                .findByUserIdAndCreatedAtBetweenOrderByCreatedAtAsc(userId, startDate, endDate);

        Map<String, List<Map<String, Object>>> trends = new HashMap<>();
        
        analyses.forEach(analysis -> {
            String emotionType = analysis.getEmotionType();
            if (!trends.containsKey(emotionType)) {
                trends.put(emotionType, new ArrayList<>());
            }
            
            trends.get(emotionType).add(Map.of(
                    "score", analysis.getEmotionScore(),
                    "date", analysis.getCreatedAt()
            ));
        });

        return Map.of("emotionTrends", trends);
    }
} 