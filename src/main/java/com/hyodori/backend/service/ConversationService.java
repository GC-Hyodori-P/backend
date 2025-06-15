package com.hyodori.backend.service;

import com.hyodori.backend.domain.Conversation;
import com.hyodori.backend.domain.ConversationType;
import com.hyodori.backend.domain.User;
import com.hyodori.backend.repository.ConversationRepository;
import com.hyodori.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GPTService gptService;

    @Transactional
    public Conversation createConversation(Long userId, ConversationType type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String message = gptService.generateConversation(type, user);

        Conversation conversation = new Conversation();
        conversation.setUser(user);
        conversation.setType(type);
        conversation.setMessage(message);
        conversation.setUserResponse(null); // 아직 응답하지 않은 상태

        return conversationRepository.save(conversation);
    }

    @Transactional
    public Conversation updateUserResponse(Long conversationId, Boolean response) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        conversation.setUserResponse(response);
        return conversationRepository.save(conversation);
    }

    @Transactional(readOnly = true)
    public List<Conversation> getUserConversations(Long userId) {
        return conversationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Transactional(readOnly = true)
    public Conversation getConversation(Long id) {
        return conversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
    }
} 