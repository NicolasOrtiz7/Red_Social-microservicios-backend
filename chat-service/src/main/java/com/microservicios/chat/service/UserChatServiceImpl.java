package com.microservicios.chat.service;

import com.microservicios.chat.entity.UserChat;
import com.microservicios.chat.repository.UserChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChatServiceImpl implements UserChatService{

    @Autowired
    private UserChatRepository repository;

    @Override
    public List<UserChat> getAllChats() {
        return repository.findAll();
    }

    @Override
    public List<UserChat> findByChatId(Long chatId) {
        return repository.findByChatId(chatId);
    }

    @Override
    public List<UserChat> sendMessage(UserChat message) {
        repository.save(message);
        List<UserChat> newList = findByChatId(message.getChatId());
        return newList;
    }
}
