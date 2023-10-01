package com.socialnetwork.post.controller;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    private List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/user/{id}")
    private List<Post> findByUserId(@PathVariable Long id){
        return postService.findPostByUserId(id);
    }

    @PostMapping()
    private Post savePost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @DeleteMapping("/delete/{postId}")
    private void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
