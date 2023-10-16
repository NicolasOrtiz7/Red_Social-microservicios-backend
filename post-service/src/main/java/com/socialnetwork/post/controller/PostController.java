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
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private IPostService postService;
    @Autowired
    private ILikeService likeService;
    @Autowired
    private ICommentService commentService;

// ------------- CRUD POST ----------------
    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/user/{userId}") // Busca todos los posts de un usuario
    public List<Post> findPostsByUserId(@PathVariable Long userId){
        return postService.findPostsByUserId(userId);
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


    // ------------- LIKES ----------------
    @GetMapping("/post/{postId}/likes")
    public List<Like> findLikesByPostId(@PathVariable Long postId){
        return likeService.findByPostId(postId);
    }

    @PostMapping("/post/{postId}/like")
    public void sendLike(@PathVariable Long postId, @RequestBody Like like){
        Post post = new Post();
        post.setId(postId);
        like.setPost(post);
        likeService.saveLike(postId, like);
    }


    // ------------- COMMENTS ----------------
    @GetMapping("/post/{postId}/comments")
    public List<Comment> findCommentsByPostId(@PathVariable Long postId){
        return commentService.findByPostId(postId);
    }

    @PostMapping("/post/{postId}/comment")
    public void sendComments(@PathVariable Long postId, @RequestBody Comment comment){
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        commentService.saveComment(comment);
    }



}
