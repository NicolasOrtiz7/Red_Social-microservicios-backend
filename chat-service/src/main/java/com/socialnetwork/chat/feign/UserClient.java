package com.socialnetwork.chat.feign;

import com.socialnetwork.chat.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "http://localhost:8003/users")
public interface UserClient {

    @GetMapping("/{id}")
    User findUserById(@PathVariable Long id);

}
