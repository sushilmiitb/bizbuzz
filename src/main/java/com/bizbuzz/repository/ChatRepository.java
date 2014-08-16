package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat,Long>{

  @Query("select c from "
      + "Chat c inner join c.sender p "
      + "where p.id=?1" )
  List<Chat> findAllChatsBySenderId(Long senderId);
  
  @Query("select c from "
      + "Chat c inner join c.chatRoom cr "
      + "where cr.id=?1" )
  List<Chat> findChatsByChatRoomId(Long chatRoomId);
 
  /*
  @Query("delete from Chat c inner join c.chatRoom cr "
      + "where cr.id=?1")
  void deleteAllChatsByChatRoomId(Long chatRoomid);
  */
  @Query("select c from "
      + "Chat c inner join c.chatRoom cr inner join c.item i "
      + "where cr.id=?1 and i.id=?2") 
  List<Chat> findAllChatsByChatRoomIdAndItemId(Long chatRoomId,Long itemId);
  
  
  @Query("select c from "
      +"Chat c inner join c.chatRoom cr inner join c.item i " 
      +"where cr.id=?1 and i.id is not null order by c.createdAt desc")
  List<Chat> findChatsByChatRoomIdAndItemIdNotNull(Long chatRoomId);
  
  
}  
