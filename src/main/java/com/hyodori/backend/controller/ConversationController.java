package com.hyodori.backend.controller;

import com.hyodori.backend.domain.Conversation;
import com.hyodori.backend.domain.ConversationType;
import com.hyodori.backend.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/{userId}")
    public ResponseEntity<Conversation> createConversation(
            @PathVariable Long userId,
            @RequestParam ConversationType type) {
        return ResponseEntity.ok(conversationService.createConversation(userId, type));
    }

    @PutMapping("/{id}/response")
    public ResponseEntity<Conversation> updateUserResponse(
            @PathVariable Long id,
            @RequestParam Boolean response) {
        return ResponseEntity.ok(conversationService.updateUserResponse(id, response));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Conversation>> getUserConversations(@PathVariable Long userId) {
        return ResponseEntity.ok(conversationService.getUserConversations(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversation(@PathVariable Long id) {
        return ResponseEntity.ok(conversationService.getConversation(id));
    }
} 