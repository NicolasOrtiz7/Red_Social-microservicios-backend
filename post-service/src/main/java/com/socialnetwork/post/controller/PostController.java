package com.socialnetwork.post.controller;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.service.ICommentService;
import com.socialnetwork.post.service.ILikeService;
import com.socialnetwork.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private ICommentService commentService;


    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/user/{id}") // Busca todos los posts de un usuario
    public List<Post> findByUserId(@PathVariable Long id){
        return postService.findPostByUserId(id);
    }

    @GetMapping("/post/{postId}")
    public Post findPostById(@PathVariable Long postId){
        return postService.findPostById(postId);
    }


    @PostMapping()
    public Post savePost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }


    // -------------------------------------------------
    @GetMapping("/likes/{postId}")
    public List<Like> findLikesByPostId(@PathVariable Long postId){
        return likeService.findByPostId(postId);
    }

    @GetMapping("/comments/{postId}")
    public List<Comment> findCommentsByPostId(@PathVariable Long postId){
        return commentService.findByPostId(postId);
    }

}
