package com.socialnetwork.post.service.impl;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.feign.UserClient;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.repository.ICommentRepository;
import com.socialnetwork.post.repository.IPostRepository;
import com.socialnetwork.post.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private UserClient userClient;


    // Busca los comentarios de un post, y a cada LikeEntity le asigna el respectivo User
    @Override
    public List<Comment> findByPostId(Long postId) {

        // Busca si el post existe
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("No existe el Post"));

        List<Comment> commentsList = commentRepository.findByPostId(postId);

        // Busca los User en la base de datos y se lo asigna a cada CommentEntity
        for (Comment comment : commentsList){
            User user = userClient.findUserById(comment.getUserId());
            comment.setUserOwner(user);
        }

        return commentsList;
    }
}
