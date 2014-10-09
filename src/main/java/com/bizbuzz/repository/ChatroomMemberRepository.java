package com.bizbuzz.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bizbuzz.model.ChatroomMember;



@Repository
@Transactional
public interface ChatroomMemberRepository extends JpaRepository<ChatroomMember, Long>{

 // ChatroomMember  findByChatroomIdAndMemberId(Long chatroomId, Long memberId);
  
  @Modifying//(clearAutomatically = true)
  @Query("update ChatroomMember cm set cm.lastAccess=?1 where cm.chatroomId=?2 and cm.memberId=?3")
  void updateChatroomMember(Date lastAccess,Long chatroomId,Long memberId);
  

}
