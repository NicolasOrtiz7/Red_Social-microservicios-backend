package com.microservicios.chat.repository;

import com.microservicios.chat.entity.ChatEntity;
import com.microservicios.chat.entity.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    List<UserChat> findByChatId(Long chatId);

}
