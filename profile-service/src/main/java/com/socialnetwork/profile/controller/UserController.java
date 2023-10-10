package com.socialnetwork.profile.controller;

import com.socialnetwork.profile.dto.UserDTO;
import com.socialnetwork.profile.entity.User;
import com.socialnetwork.profile.feign.ChatClient;
import com.socialnetwork.profile.feign.PostClient;
import com.socialnetwork.profile.model.Chat;
import com.socialnetwork.profile.model.Message;
import com.socialnetwork.profile.model.Post;
import com.socialnetwork.profile.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /*
    * Los métodos que conectan con los servicios de Post y Chat son innecesarios, no los utilizo en
    * ningún momento por ahora.
    * */

    @Autowired
    private IUserService userService;
    @Autowired
    private PostClient postClient;
    @Autowired
    private ChatClient chatClient;


    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    // ------------ CRUD User ------------

    @GetMapping("/{id}") // Ver por qué no devuelve la lista de followers. Igual sería una lista infinita (arreglar con dto).
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/save/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }


    // ------------ Seguidores ------------

    // Obtiene los seguidores del usuario
    @GetMapping("/followers/{id}")
    public List<UserDTO> findFollowersByUserId(@PathVariable Long id){
        return userService.findFollowersByUserId(id);
    }

    // Obtiene los seguidos del usuario
    @GetMapping("/followed/{id}")
    public List<UserDTO> findUsersByFollowerId(@PathVariable Long id){
        return userService.findUsersByFollowerId(id);
    }

    // Seguir a un usuario
    @PutMapping("/follow/{user_id}/{follower_id}")
    public void follow(@PathVariable("user_id")Long user_id, @PathVariable("follower_id") Long follower_id){
        userService.followUser(user_id, follower_id);
    }

    // Dejar de seguir a un usuario
    @PutMapping("/unfollow/{user_id}/{unfollower_id}")
    public void unfollow(@PathVariable("user_id")Long user_id, @PathVariable("unfollower_id") Long follower_id){
        userService.unfollowUser(user_id, follower_id);
    }

    // ------------ Posts ------------

    @GetMapping("/posts/all")
    public List<Post> getAllPost(){
        return postClient.findAllPosts();
    }

    @GetMapping("/posts/{userId}")
    public List<Post> findByUserId(@PathVariable Long userId){
        return postClient.findPostsByUserId(userId);
    }

    @DeleteMapping("/posts/delete/{postId}")
    public void deletePost(@PathVariable Long postId){
        postClient.deletePost(postId);
    }

    // ------------ Chats ------------

    // Obtiene los mensajes de un chat
    @GetMapping("/chat/{chatId}/messages")
    public List<Message> findMessagesByChatId(@PathVariable Long chatId){
        return chatClient.findMessagesByChatId(chatId);
    }

    // Obtiene los chats de un usuario
    @GetMapping("/{userId}/chats")
    public List<Chat> findChatsByUserId(@PathVariable Long userId){
        return chatClient.findChatsByUserId(userId);
    }
}
