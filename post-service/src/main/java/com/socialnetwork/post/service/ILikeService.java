package com.socialnetwork.post.service;

import com.socialnetwork.post.entity.Like;

import java.util.List;

public interface ILikeService {

    List<Like> findByPostId(Long postId);

    void saveLike(Long postId, Like like);

}
