package com.hyodori.backend.controller;

import com.hyodori.backend.service.AzureOpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final AzureOpenAIService openAIService;

    @Autowired
    public OpenAIController(AzureOpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/ask")
    public String askOpenAI(@RequestParam String question) {
        return openAIService.getResponseFromOpenAI(question);
    }
}