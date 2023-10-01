package com.socialnetwork.profile.model;


import lombok.Data;

import java.util.Date;
@Data
public class Comment {

    private Long id;
    private Long userId;
    private Post postId;
    private String comment;
    private Date createdAt = new Date();

}
