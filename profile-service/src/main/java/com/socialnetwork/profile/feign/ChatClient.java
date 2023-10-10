package com.socialnetwork.profile.feign;

import com.socialnetwork.profile.model.Chat;
import com.socialnetwork.profile.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "chat-service", url = "http://localhost:8001/chats")
public interface ChatClient {

    @GetMapping("/chat/{chatId}")
    List<Message> findMessagesByChatId(@PathVariable Long chatId);

    @GetMapping("/user/{userId}")
    List<Chat> findChatsByUserId(@PathVariable Long userId);

}
