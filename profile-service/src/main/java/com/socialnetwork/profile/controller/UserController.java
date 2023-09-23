package com.socialnetwork.profile.controller;

import com.socialnetwork.profile.dto.UserDTO;
import com.socialnetwork.profile.entity.User;
import com.socialnetwork.profile.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    // ------------ CRUD User ------------

    @GetMapping("/{id}")
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

    // ------------------------


}
