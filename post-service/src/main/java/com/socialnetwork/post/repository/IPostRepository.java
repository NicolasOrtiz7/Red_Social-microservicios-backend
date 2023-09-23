package com.socialnetwork.post.repository;

import com.socialnetwork.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long id);

}
