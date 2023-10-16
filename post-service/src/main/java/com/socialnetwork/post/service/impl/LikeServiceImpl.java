package com.socialnetwork.post.service.impl;

import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.feign.UserClient;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.repository.ILikeRepository;
import com.socialnetwork.post.repository.IPostRepository;
import com.socialnetwork.post.service.ILikeService;
import com.socialnetwork.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private UserClient userClient;



    // Busca los likes de un post, y a cada LikeEntity le asigna el respectivo User
    @Override
    public List<Like> findByPostId(Long postId){

        // Busca si el post existe
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("No existe el Post"));

        List<Like> likesList = likeRepository.findByPostId(postId);

        // Busca los User en la base de datos y se lo asigna a cada LikeEntity
        for (Like like : likesList){
            User user = userClient.findUserById(like.getUserId());
            like.setUserOwner(user);
        }

        return likesList;
    }

    @Override
    public void saveLike(Long postId, Like like) {

        Optional<Like> likeExists = likeRepository.findByUserIdAndPostId(like.getUserId(), postId);

        if (likeExists.isEmpty()) likeRepository.save(like);
        // else
        likeRepository.deleteById(likeExists.get().getId());

    }

}
