package com.socialnetwork.profile.model;

import lombok.Data;

@Data
public class Chat {

    private Long id;
    private Long senderUserId;
    private Long receiverUserId;


}
