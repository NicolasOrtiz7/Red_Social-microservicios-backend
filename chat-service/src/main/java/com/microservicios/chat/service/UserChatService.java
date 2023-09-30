package com.microservicios.chat.service;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;

import java.util.List;

public interface UserChatService {

    List<UserChat> getAllChats();

    List<UserChat> findByChatId(Long chatId);

    List<UserChat> sendMessage(UserChat message);

}
