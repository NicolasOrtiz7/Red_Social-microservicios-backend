package com.microservicios.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_chats")
public class UserChat {

    // Esta es la tabla donde se guardan los mensajes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime = new Date();

    private Long chatId;

    private Long senderUserId;

}
