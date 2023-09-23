package com.socialnetwork.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String profilePicture;
    private String coverPicture;

}
