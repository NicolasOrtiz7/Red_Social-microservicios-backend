package com.socialnetwork.profile.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private Long messageId;
    private String content;
    private Date datetime;
    private Long chatId;
    private Long senderUserId;

}
