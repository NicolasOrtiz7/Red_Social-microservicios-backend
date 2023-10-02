package com.socialnetwork.post.repository;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

}
