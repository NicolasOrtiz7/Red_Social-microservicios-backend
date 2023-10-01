package com.microservicios.chat.service;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;

import java.util.List;

public interface ChatService {

    List<ChatEntity> findChatsByUserId(Long senderId);

}
