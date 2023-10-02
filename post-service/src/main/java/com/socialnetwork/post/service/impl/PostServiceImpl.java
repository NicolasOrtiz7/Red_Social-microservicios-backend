package com.socialnetwork.post.service.impl;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.feign.UserClient;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.repository.IPostRepository;
import com.socialnetwork.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService, UserClient {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe ese Post"));
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



    // ----------- Users Feign Client -----------------

    @Override
    public User findUserById(Long id) {
        return null;
    }


}
