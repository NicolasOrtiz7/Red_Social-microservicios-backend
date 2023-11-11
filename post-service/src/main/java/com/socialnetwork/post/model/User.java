package com.socialnetwork.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private Long id;
    private String name;
    private String username;
    private String profilePicture;

    public User(Long id) {
        this.id = id;
    }
}
