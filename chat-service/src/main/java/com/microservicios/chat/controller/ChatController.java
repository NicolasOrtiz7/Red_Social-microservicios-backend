package com.microservicios.chat.controller;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;
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
    private UserChatService chatService;


    @GetMapping
    public List<UserChat> getAllChats(){
        return chatService.getAllChats();
    }

    @GetMapping("/{chat_id}")
    public List<UserChat> findByChatId(@PathVariable Long chat_id){
        return chatService.findByChatId(chat_id);
    }

    @PostMapping("/{chat_id}")
    public List<UserChat> sendMessage(@PathVariable Long chat_id, @RequestBody UserChat message){
        message.setChatId(chat_id);
        return chatService.sendMessage(message);
    }


}
