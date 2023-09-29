package com.microservicios.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private Long senderUserId;
    private Long receiverUserId;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

//    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
//    private List<UserMessages> userMessagesList = new ArrayList<>();

}
