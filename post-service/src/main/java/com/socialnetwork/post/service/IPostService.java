package com.socialnetwork.post.service;

import com.socialnetwork.post.entity.Post;

import java.util.List;

public interface IPostService {

    List<Post> findAll();

    Post findPostById(Long postId);

    List<Post> findPostsByUserId(Long userId);

    Post savePost(Post post);

    Post updatePost(Post post);

    Post deletePost(Long postId);

}
