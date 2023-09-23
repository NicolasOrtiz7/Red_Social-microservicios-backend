package com.socialnetwork.profile.repository;

import com.socialnetwork.profile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.followers FROM User u WHERE u.id = :userId")
    List<User> findFollowersByUserId(Long userId);

    @Query("SELECT u FROM User u JOIN u.followers f WHERE f.id = :followerId")
    List<User> findUsersByFollowerId(Long followerId);

}
