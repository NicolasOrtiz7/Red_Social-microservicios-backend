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
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private UserClient userClient;


    @Override
    public List<Post> findAll() {

        List<Post> postsList = postRepository.findAll();

        for (Post post : postsList){
            User user = userClient.findUserById(post.getUserId());
            post.setUserOwner(user);
        }

        return postsList;
    }

    @Override
    public Post findPostById(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("No existe ese Post"));
        User user = userClient.findUserById(post.getUserId());

        post.setUserOwner(user);

        return post;
    }

    @Override
    public List<Post> findPostsByUserId(Long userId) {

        List<Post> postsList = postRepository.findByUserId(userId);

        for (Post post : postsList){
            User user = userClient.findUserById(post.getUserId());
            post.setUserOwner(user);
        }

        return postsList;
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
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

}
