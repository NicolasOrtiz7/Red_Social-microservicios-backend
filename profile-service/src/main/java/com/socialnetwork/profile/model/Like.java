package com.socialnetwork.profile.model;

import lombok.Data;

@Data
public class Like {

    private Long id;
    private Long userId;
    private Post postId;

}
