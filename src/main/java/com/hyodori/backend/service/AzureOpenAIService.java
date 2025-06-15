package com.hyodori.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AzureOpenAIService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.endpoint}")
    private String openaiApiEndpoint;

    @Value("${openai.api.version}")
    private String openaiApiVersion;

    // 프롬포트
    private static final String SYSTEM_PROMPT = "당신은 할머니,할아버지에게 GDS설문 문항을 손녀AI가 친근한 말투로 물어보는 것처럼 대화형으로 전환하는 AI입니다. 제공된 설문 문항에 대해 전환한 값만 제공하세요.";
    public String getResponseFromOpenAI(String userMessage) {
        WebClient client = WebClient.builder()
                .baseUrl(openaiApiEndpoint)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("api-key", openaiApiKey)
                .build();

        String requestBody = String.format("""
        {
            "messages": [
                {"role": "system", "content": "%s"},
                {"role": "user", "content": "%s"}
            ],
            "max_tokens": 50
        }
        """, SYSTEM_PROMPT, userMessage);

        String response = client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/openai/deployments/gpt-4o-mini/chat/completions")
                        .queryParam("api-version", openaiApiVersion)
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractContentFromResponse(response);
    }

    // JSON 응답에서 content 값만 추출
    private String extractContentFromResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            return root.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            return "오류가 발생했습니다: " + e.getMessage();
        }
    }
}