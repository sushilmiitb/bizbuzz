package com.bizbuzz.service;

import java.util.Date;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.ChatroomMember;
import com.bizbuzz.model.Party;

public interface ChatroomMemberService {

  public void createChatroomMember(ChatRoom chatroom,Party member,Date creationDate);
  public void updateChatroomMemberByChatroomIdAndMemberId(Date lastAccess,Long chatroomId,Long memberId);
  
  //public ChatroomMember getChatroomMember(Long chatroomId,Long memberId);
  
 // public void saveChatroomMember(ChatroomMember chatroomMember);
  
}
