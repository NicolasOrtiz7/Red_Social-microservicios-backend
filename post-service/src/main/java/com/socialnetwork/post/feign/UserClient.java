package com.socialnetwork.post.feign;

import com.socialnetwork.post.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "http://localhost:8003/users")
public interface UserClient {

    @GetMapping("/{id}")
    User findUserById(@PathVariable Long id);

}
