package com.socialnetwork.profile.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Post {

    private Long id;
    private Long userId;
    private String description;
    private String image;
    private Date createdAt;
    private List<Like> likes;
    private List<Comment> comments;

}
