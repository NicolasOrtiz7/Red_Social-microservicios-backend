package com.socialnetwork.chat.controller;

import com.socialnetwork.chat.entity.ChatEntity;
import com.socialnetwork.chat.entity.UserChat;
import com.socialnetwork.chat.service.IChatService;
import com.socialnetwork.chat.service.IUserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // hasta implementar websockets
@RequestMapping("/chats")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    @Autowired // Estos son los mensajes
    private IUserChatService userChatService;
    @Autowired // Este es el chat
    private IChatService chatService;


// No usar este método. Devuelve todos los mensajes de todos los chats de todos los usuarios
//    @GetMapping
//    public List<UserChat> getAllChatMessages(){
//        return userChatService.getAllChats();
//    }

    // Obtiene todos los mensajes de un chat
    @GetMapping("/chat/{chatId}")
    public List<UserChat> findByChatId(@PathVariable Long chatId){
        return userChatService.findByChatId(chatId);
    }


    @PostMapping("/chat/{chatId}")
    public List<UserChat> sendMessage(@PathVariable Long chatId, @RequestBody UserChat message){
        message.setChatId(chatId);
        return userChatService.sendMessage(message);
    }

    // -----------------------------------------

    // Busca todos los chats de un usuario
    @GetMapping("/user/{userId}")
    public List<ChatEntity> findChatsByUserId(@PathVariable Long userId){
        return chatService.findChatsByUserId(userId);
    }

    @GetMapping("/chat/search/{user1Id}/{user2Id}")
    public ChatEntity findChatByUserIds(@PathVariable Long user1Id, @PathVariable Long user2Id){
        ChatEntity chatExists = chatService.findChatByUserIds(user1Id, user2Id);

        // Busca si existe un chat entre ambos usuarios. Si no existe, lo crea.
        if (chatExists == null){
            ChatEntity newChat = ChatEntity.builder()
                    .senderUserId(user1Id)
                    .receiverUserId(user2Id)
                    .build();

            return chatService.createChat(newChat);
        }
        else return chatExists;

    }

    // Busca los participantes de un chat
    @GetMapping("/chat/{chatId}/participants")
    public ChatEntity findUsersInChatByChatId(@PathVariable Long chatId){
        return chatService.findById(chatId);
    }

    @PostMapping("/chat/create")
    public ChatEntity createNewChat(ChatEntity chat){
        return chatService.createChat(chat);
    }


}
