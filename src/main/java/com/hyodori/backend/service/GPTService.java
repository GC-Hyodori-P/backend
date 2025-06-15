package com.hyodori.backend.service;

import com.hyodori.backend.domain.ConversationType;
import com.hyodori.backend.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class GPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateConversation(ConversationType type, User user) {
        String prompt = buildPrompt(type, user);
        return callGPT(prompt);
    }

    private String buildPrompt(ConversationType type, User user) {
        StringBuilder prompt = new StringBuilder();
        
        if (type == ConversationType.GDS_SURVEY) {
            prompt.append("당신은 따뜻하고 공감적인 AI 친구입니다. ")
                  .append("다음 GDS 설문 문항을 친근하고 자연스러운 대화체로 변환해주세요. ")
                  .append("예/아니오로 대답할 수 있도록 해주세요. ")
                  .append("사용자의 나이와 상황을 고려하여 존댓말을 사용해주세요.\n\n")
                  .append("원본 문항: \"대체로 만족스럽게 생활하고 계십니까?\"\n")
                  .append("사용자 정보:\n")
                  .append("- 이름: ").append(user.getName()).append("\n");
        } else {
            prompt.append("당신은 따뜻하고 공감적인 AI 친구입니다. ")
                  .append("다음 사용자 정보를 바탕으로 일상적인 대화를 생성해주세요. ")
                  .append("예/아니오로 대답할 수 있는 질문이어야 합니다. ")
                  .append("사용자의 나이와 상황을 고려하여 존댓말을 사용해주세요.\n\n")
                  .append("사용자 정보:\n")
                  .append("- 이름: ").append(user.getName()).append("\n")
                  .append("- 대화 주제: 일상, 취미, 건강, 가족 관계 등\n");
        }

        return prompt.toString();
    }

    private String callGPT(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", Arrays.asList(
            Map.of("role", "system", "content", "당신은 따뜻하고 공감적인 AI 친구입니다. 사용자의 상황과 감정을 이해하고 공감하며, 존댓말을 사용하여 대화합니다."),
            Map.of("role", "user", "content", prompt)
        ));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 150);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                Map.class
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> choice = choices.get(0);
                    Map<String, String> message = (Map<String, String>) choice.get("message");
                    return message.get("content");
                }
            }
            return "죄송합니다. 응답을 생성하는데 문제가 발생했습니다.";
        } catch (Exception e) {
            return "죄송합니다. 서비스에 일시적인 문제가 발생했습니다.";
        }
    }
} 