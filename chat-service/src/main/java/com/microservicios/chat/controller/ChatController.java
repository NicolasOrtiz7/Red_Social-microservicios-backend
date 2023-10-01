package com.microservicios.chat.controller;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;
import com.microservicios.chat.service.ChatService;
import com.microservicios.chat.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // hasta implementar websockets
@RequestMapping("/chats")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {


    @Autowired
    private UserChatService userChatService;

    @Autowired
    private ChatService chatService;


    @GetMapping
    public List<UserChat> getAllChats(){
        return userChatService.getAllChats();
    }

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


}
