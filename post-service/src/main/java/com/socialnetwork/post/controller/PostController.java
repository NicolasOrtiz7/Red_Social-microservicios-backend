package com.socialnetwork.post.controller;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.service.ICommentService;
import com.socialnetwork.post.service.ILikeService;
import com.socialnetwork.post.service.IPostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @CircuitBreaker(name = "profileCB", fallbackMethod = "fallbackUsers")
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/user/{id}") // Busca todos los posts de un usuario
    @CircuitBreaker(name = "profileCB", fallbackMethod = "fallbackUsers")
    public List<Post> findPostsByUserId(@PathVariable Long id){
        return postService.findPostsByUserId(id);
    }

    @GetMapping("/post/{postId}")
    public Post findPostByPostId(@PathVariable Long postId){
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

    // -------------------------------------------------
    public ResponseEntity<String> fallbackUsers(RuntimeException e){
        return new ResponseEntity<>("El servicio está deshabilitado", HttpStatus.OK);
    }

}
