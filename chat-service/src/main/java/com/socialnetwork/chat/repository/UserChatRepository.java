package com.socialnetwork.chat.repository;

import com.socialnetwork.chat.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    List<UserChat> findByChatId(Long chatId);

}
