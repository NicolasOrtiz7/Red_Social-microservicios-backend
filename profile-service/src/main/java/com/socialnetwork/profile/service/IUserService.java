package com.socialnetwork.profile.service;

import com.socialnetwork.profile.dto.UserDTO;
import com.socialnetwork.profile.entity.User;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();

    User findUserById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);

    List<UserDTO> findFollowersByUserId(Long userId);

    List<UserDTO> findUsersByFollowerId(Long followerId);

    User followUser(Long user_id, Long follower_id);

    User unfollowUser(Long user_id, Long unfollowed_id);

}
