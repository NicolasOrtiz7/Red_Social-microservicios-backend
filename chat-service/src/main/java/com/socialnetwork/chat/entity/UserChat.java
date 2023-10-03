package com.socialnetwork.chat.entity;

import com.socialnetwork.chat.model.User;
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

    // Esta entidad debería llamarse Messages. Acá se guardan los mensajes. Después cambiar.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime = new Date();

    private Long chatId;

    private Long senderUserId;

    @Transient
    private User senderUser;

}
