package com.microservicios.chat.controller;

import com.microservicios.chat.entity.MessageEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
// se usaría @RequestMapping,
// pero la clase WebSocketConfig hace que los mensajes se envíen a localhost:8080/app/***
public class ChatController {

    @MessageMapping("/chat") // Reemplaza a @RequestMapping o @PostMapping, etc.
    @SendTo("/topic/messages") // Envía el mensaje a los usuarios suscritos a /topic/messages
    public MessageEntity sendMessage(MessageEntity message){
        // // Procesa y guarda el mensaje en la base de datos si es necesario

        return message;
    }

}
