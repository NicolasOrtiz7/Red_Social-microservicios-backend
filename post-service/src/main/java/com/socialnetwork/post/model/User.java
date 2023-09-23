package com.socialnetwork.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;
    private String name;
    private String username;
    private String profilePicture;

}
