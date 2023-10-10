package com.socialnetwork.chat.service;

import com.socialnetwork.chat.entity.ChatEntity;

import java.util.List;

public interface IChatService {

    List<ChatEntity> findChatsByUserId(Long senderId);

    ChatEntity findById(Long chatId);

    ChatEntity findChatByUserIds(Long user1, Long user2);

    ChatEntity createChat(ChatEntity chat);

}
