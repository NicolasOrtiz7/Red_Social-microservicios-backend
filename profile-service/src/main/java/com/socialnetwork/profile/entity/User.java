package com.socialnetwork.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotNull @NotBlank
    @Email
    private String email;

    @Size(max = 255)
    private String description;

    private String profilePicture = "no-image.png";
    private String coverPicture = "no-image2.png";

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )

    private Set<User> followers = new HashSet<>();

}
