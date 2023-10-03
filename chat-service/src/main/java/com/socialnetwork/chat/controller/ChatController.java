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
    @GetMapping("/{chatId}")
    public List<UserChat> findByChatId(@PathVariable Long chatId){
        return userChatService.findByChatId(chatId);
    }

    @PostMapping("/{chatId}")
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

    // Busca los participantes de un chat
    @GetMapping("/chat/{chatId}")
    public ChatEntity findUsersInChatByChatId(@PathVariable Long chatId){
        return chatService.findById(chatId);
    }


}
