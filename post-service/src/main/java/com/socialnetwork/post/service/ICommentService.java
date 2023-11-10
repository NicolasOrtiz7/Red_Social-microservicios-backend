package com.socialnetwork.post.service;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Post;

import java.util.List;

public interface ICommentService {

    List<Comment> findByPostId(Long postId);

    void saveComment(Long postId, Comment comment);

}
