package com.socialnetwork.post.repository;

import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPostId(Long postId);

}
