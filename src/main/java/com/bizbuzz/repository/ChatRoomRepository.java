package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;

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
  @Query("select distinct c from ChatRoom c inner join c.members p1 inner join c.members p2 where p1.id=?1 and p2.id=?2 ")
  List<ChatRoom> findChatRoomByMembers(Long toPartyId,Long fromPartyId);

  @Query("select c " +
      "from Person m inner join m.chatrooms crms inner join crms.chatroom cr inner join cr.chats c " +
      "where m.id=?1 and " +
      "cr.updatedAt=c.createdAt " +
      "order by cr.updatedAt desc ")
  List<Chat> findSortedChatsOfPerson(Long personId);

  @Query("select c " +
      "from Person m inner join m.chatrooms crms inner join crms.chatroom cr inner join cr.chats c " +
      "where m.id=?1 "
      + "and c.item.id=?2 and " +
      "cr.updatedAt=c.createdAt " +
      "order by cr.updatedAt desc ")
  List<Chat> findSortedItemChatsOfPerson(Long personId, Long itemId);

  @Query("select p from ChatRoom cr inner join cr.members cm inner join cm.member p where cr.id=?1")
  List<Person> findAllMembersOfChatRoomByChatRoomId(Long chatRoomId); 

  @Query("select cr from Person p inner join p.chatrooms crs inner join crs.chatroom cr inner join " +
  		"cr.members cms inner join cms.member m " +
      "where m.id=?1 and p.id<>?1 and cr.updatedAt is null order by p.firstName ")
  List<ChatRoom> findAllNewSortedChatRoomsOfPerson(Long personId);
 /* 
  @Query("select distinct cr " +
      "from Person m inner join m.chatrooms cr inner join cr.chats c " +
      "where m.id=?1 "
      + "and c.item.id!=?2 "
      + "order by m.firstName")
  List<ChatRoom> findAllChatRoomsByNotItemIdAndPersonIdOrderedByPersonName(Long personId, Long itemId);
*/
}
