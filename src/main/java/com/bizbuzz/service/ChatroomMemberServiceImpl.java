package com.bizbuzz.service;

import java.security.Key;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.ChatroomMember;
import com.bizbuzz.model.Party;
//import com.bizbuzz.model.ChatroomMemberId;
import com.bizbuzz.repository.ChatroomMemberRepository;


@Service
public class ChatroomMemberServiceImpl implements ChatroomMemberService{

 @Autowired
 ChatroomMemberRepository chatroomMemberRepository;
  
  public void createChatroomMember(ChatRoom chatroom, Party member,Date creationDate) {
      ChatroomMember chatroomMember = new ChatroomMember(chatroom, member,creationDate);
      chatroomMemberRepository.save(chatroomMember);
  }
  
  public void updateChatroomMemberByChatroomIdAndMemberId(Date lastAccess,Long chatroomId,Long memberId){
       chatroomMemberRepository.updateChatroomMember(lastAccess, chatroomId,memberId);
  }
/*
  @Override
  public ChatroomMember getChatroomMember(Long chatroomId,Long memberId) {
/*          ChatroomMemberId key = new ChatroomMemberId();
          key.setChatroomId(chatroomId);
          Key.setMemberId(memberId);
          
    return chatroomMemberRepository.findOne(key);
 
   return chatroomMemberRepository.findByChatroomIdAndMemberId(chatroomId, memberId); 
  }

  @Override
  public void saveChatroomMember(ChatroomMember chatroomMember) {
    chatroomMemberRepository.save(chatroomMember);  
  }
*/

}
