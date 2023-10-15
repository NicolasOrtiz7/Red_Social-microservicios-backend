package com.socialnetwork.profile.dto;

import com.socialnetwork.profile.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String description;
    private Set<User> followers = new HashSet<>();
    private String profilePicture;
    private String coverPicture;

}
