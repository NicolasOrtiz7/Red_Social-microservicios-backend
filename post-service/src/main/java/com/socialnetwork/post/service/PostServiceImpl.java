package com.socialnetwork.post.service;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService{

    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPostById(Long id) {
        return null;
    }

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
