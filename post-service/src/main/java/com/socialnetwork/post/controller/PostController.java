package com.socialnetwork.post.controller;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.service.ICommentService;
import com.socialnetwork.post.service.ILikeService;
import com.socialnetwork.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}") // Busca todos los posts de un usuario
    public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(postService.findPostsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId){
        return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
    }


    // ------------- LIKES ----------------
    @GetMapping("/post/{postId}/likes")
    public ResponseEntity<List<Like>> findLikesByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(likeService.findByPostId(postId), HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/like")
    public ResponseEntity<Void> sendLike(@PathVariable Long postId, @RequestBody Like like){
        likeService.saveLike(postId, like);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ------------- COMMENTS ----------------
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<Comment>> findCommentsByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.findByPostId(postId), HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<Void> sendComments(@PathVariable Long postId, @RequestBody Comment comment){
        commentService.saveComment(postId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
