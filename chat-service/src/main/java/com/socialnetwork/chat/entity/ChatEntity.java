package com.socialnetwork.chat.entity;

import com.socialnetwork.chat.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "chats")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    private Long senderUserId;
    private Long receiverUserId;

    @Transient
    private User senderUser;
    @Transient
    private User receiverUser;


}
