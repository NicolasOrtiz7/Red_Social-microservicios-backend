package com.socialnetwork.post.service.impl;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.feign.UserClient;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.repository.IPostRepository;
import com.socialnetwork.post.service.IPostService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private UserClient userClient;


    @Override
    public List<Post> findAll() {
        List<Post> postsList = postRepository.findAllByOrderByIdDesc();

        // Busca el User que creó el Post, si no lo encuentra quita el Post de la lista
        postsList = postsList.stream().filter(post -> {
            try{
                User user = userClient.findUserById(post.getUserId());
                post.setUserOwner(user);
                return true;
            }catch (FeignException e){
                return false;
            }
        }).collect(Collectors.toList());
        return postsList;
    }

    @Override
    public Post findPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("No existe ese Post"));
        try {
            User user = userClient.findUserById(post.getUserId());
            post.setUserOwner(user);
            return post;

        }catch (FeignException e){
            throw new NotFoundException("No se encontró al usuario que hizo ese Post");
        }
    }

    @Override
    public List<Post> findPostsByUserId(Long userId) {
        List<Post> postsList = postRepository.findByUserId(userId);
        User user;

        try { user = userClient.findUserById(userId); }
        catch (FeignException e) { throw new NotFoundException("No se encontró al usuario"); }

        for (Post post : postsList) post.setUserOwner(user);
        return postsList;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override // Por ahora no voy a implementar la función de editar
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post deletePost(Long postId) {
        Post deletedPost = findPostById(postId);
        postRepository.deleteById(postId);
        return deletedPost;
    }

}
