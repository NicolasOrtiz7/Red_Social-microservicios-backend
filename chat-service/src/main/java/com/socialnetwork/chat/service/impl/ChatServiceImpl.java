package com.socialnetwork.chat.service.impl;

import com.socialnetwork.chat.entity.ChatEntity;
import com.socialnetwork.chat.exception.NotFoundException;
import com.socialnetwork.chat.feign.UserClient;
import com.socialnetwork.chat.model.User;
import com.socialnetwork.chat.repository.ChatRepository;
import com.socialnetwork.chat.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements IChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserClient userClient;


    // Estos métodos devuelven datos innecesarios, podría hacer que devuelvan un DTO.
    @Override
    public List<ChatEntity> findChatsByUserId(Long senderId) {

        List<ChatEntity> chatlist = chatRepository.findChatsByUserId(senderId);

        for (ChatEntity chat : chatlist){
            User user1 = userClient.findUserById(chat.getSenderUserId());
            User user2 = userClient.findUserById(chat.getReceiverUserId());

            chat.setSenderUser(user1);
            chat.setReceiverUser(user2);
        }

        return chatlist;
    }

    @Override
    public ChatEntity findById(Long chatId) {

        ChatEntity chat = chatRepository.findById(chatId).orElseThrow(()-> new NotFoundException("No existe ese chat"));

        User user1 = userClient.findUserById(chat.getSenderUserId());
        User user2 = userClient.findUserById(chat.getReceiverUserId());

        chat.setSenderUser(user1);
        chat.setReceiverUser(user2);

        return chat;
    }

    @Override
    public ChatEntity findChatByUserIds(Long user1, Long user2) {

        ChatEntity chat = chatRepository.findChatByUserIds(user1, user2);

        chat.setSenderUser(userClient.findUserById(chat.getSenderUserId()));
        chat.setReceiverUser(userClient.findUserById(chat.getReceiverUserId()));

        return chat;
    }

    @Override
    public ChatEntity createChat(ChatEntity chat) {
        return chatRepository.save(chat);
    }


}
