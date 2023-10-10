package com.socialnetwork.chat.repository;

import com.socialnetwork.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    @Query("SELECT c FROM ChatEntity c " +
            "WHERE c.senderUserId = :userId OR c.receiverUserId = :userId")
    List<ChatEntity> findChatsByUserId(Long userId);

    @Query("SELECT c FROM ChatEntity c " +
            "WHERE ((c.senderUserId = :user1 AND c.receiverUserId = :user2) " +
            "OR (c.senderUserId = :user2 AND c.receiverUserId = :user1))")
    ChatEntity findChatByUserIds(@Param("user1") Long user1, @Param("user2") Long user2);


}
