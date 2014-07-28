package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;

@Repository
@Transactional
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>{
  @Query("select c from ChatRoom c inner join c.members p1 inner join c.members p2 where p1.id=?1 and p2.id=?2 ")
  ChatRoom findChatRoomByMembers(Long toPartyId,Long fromPartyId);
   
  @Query("select cr from ChatRoom cr inner join cr.members p where p.id=?1 order by cr.updatedAt desc")
  List<ChatRoom> findSortedChatRoomsOfPerson(Long personId);
  
  @Query("select p from ChatRoom cr inner join cr.members p where cr.id=?1 and p.id<>?2")
  Person findPersonByChatRoomId(Long chatRoomId,Long personId);
  
  
}
