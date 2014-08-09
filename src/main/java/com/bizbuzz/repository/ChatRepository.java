package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat,Long>{

  @Query("select c from "
      + "Chat c inner join c.sender p "
      + "where p.id=?1" )
  List<Chat> getChatBySenderId(Long senderId);
  
  
  @Query("select c from "
      + "Chat c inner join c.chatRoom cr "
      + "where cr.id=?1" )
  List<Chat> findChatsByChatRoomId(Long chatRoomId);
  
  /*
  @Query("delete from Chat c inner join c.chatRoom cr "
      + "where cr.id=?1")
  void deleteAllChatsByChatRoomId(Long chatRoomid);
  */
   
  
}  
