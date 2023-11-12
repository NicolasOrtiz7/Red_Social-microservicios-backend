package com.socialnetwork.post.entity;

import com.socialnetwork.post.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Solo se usa para devolver objetos JSON
    @Transient
    private User userOwner;

}
