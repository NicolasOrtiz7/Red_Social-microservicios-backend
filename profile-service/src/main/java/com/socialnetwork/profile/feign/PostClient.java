package com.socialnetwork.profile.feign;

import com.socialnetwork.profile.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "http://localhost:8002/posts")
public interface PostClient {

    @GetMapping
    List<Post> findAllPosts();

    @GetMapping("/user/{id}")
    List<Post> findPostsByUserId(@PathVariable Long id);

    @DeleteMapping("/delete/{postId}")
    void deletePost(@PathVariable Long postId);

}
