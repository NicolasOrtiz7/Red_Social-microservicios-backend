package com.microservicios.chat.service;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;
import com.microservicios.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<ChatEntity> findChatsByUserId(Long senderId) {
        return chatRepository.findChatsByUserId(senderId);
    }
}
