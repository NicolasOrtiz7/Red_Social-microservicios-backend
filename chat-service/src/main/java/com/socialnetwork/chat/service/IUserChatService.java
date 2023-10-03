package com.socialnetwork.chat.service;

import com.socialnetwork.chat.entity.UserChat;

import java.util.List;

public interface IUserChatService {

    List<UserChat> getAllChats();

    List<UserChat> findByChatId(Long chatId);

    List<UserChat> sendMessage(UserChat message);

}
