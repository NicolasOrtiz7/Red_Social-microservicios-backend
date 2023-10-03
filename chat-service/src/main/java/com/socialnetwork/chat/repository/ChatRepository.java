package com.socialnetwork.chat.repository;

import com.socialnetwork.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    @Query("SELECT c FROM ChatEntity c " +
            "WHERE c.senderUserId = :userId OR c.receiverUserId = :userId")
    List<ChatEntity> findChatsByUserId(Long userId);

}
