package com.socialnetwork.chat.service.impl;

import com.socialnetwork.chat.entity.UserChat;
import com.socialnetwork.chat.feign.UserClient;
import com.socialnetwork.chat.model.User;
import com.socialnetwork.chat.repository.UserChatRepository;
import com.socialnetwork.chat.service.IUserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChatServiceImpl implements IUserChatService {

    @Autowired
    private UserChatRepository messageRepository;
    @Autowired
    private UserClient userClient;

    @Override
    public List<UserChat> getAllChats() {
        return messageRepository.findAll();
    }

    @Override
    public List<UserChat> findByChatId(Long chatId) {

        List<UserChat> messageList = messageRepository.findByChatId(chatId);

        for (UserChat message : messageList){
            User user = userClient.findUserById(message.getSenderUserId());
            message.setSenderUser(user);
        }
        return messageList;
    }

    @Override
    public List<UserChat> sendMessage(UserChat message) {
        messageRepository.save(message);
        List<UserChat> newList = findByChatId(message.getChatId());
        return newList;
    }
}
