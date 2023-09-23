package com.socialnetwork.post.service;

import com.socialnetwork.post.entity.Post;

import java.util.List;

public interface IPostService {

    List<Post> findAll();

    List<Post> findPostById(Long id);

    List<Post> findPostByUserId(Long userId);

    Post savePost(Post post);

    Post updatePost(Post post);

    void deletePost(Long id);

}
