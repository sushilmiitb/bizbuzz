package com.bizbuzz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@IdClass(ChatroomMemberId.class)
public class ChatroomMember {

  @Id
  private Long chatroomId;
  
  @Id
  private Long memberId;

  @ManyToOne
  @PrimaryKeyJoinColumn(name="chatroom_id", referencedColumnName="id")
  private ChatRoom chatroom;
  
  @ManyToOne
  @PrimaryKeyJoinColumn(name="member_id", referencedColumnName="id")
  private Party member;
  
  private Date lastAccess;
  
  public ChatroomMember() {
    
  } 

  public ChatroomMember(ChatRoom chatroom, Party member,Date creationDate) {
    this.chatroom = chatroom;
    this.member = member;
    this.chatroomId = chatroom.getId();
    this.memberId = member.getId();
    this.lastAccess = creationDate;
  }

  public Long getChatroomId(){
    return chatroomId;
  }

  public void setChatroomId(Long chatroomId) {
    this.chatroomId = chatroomId;
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public ChatRoom getChatroom() {
    return chatroom;
  }

  public void setChatroom(ChatRoom chatroom) {
    this.chatroom = chatroom;
  }

  public Party getMember() {
    return member;
  }

  public void setMember(Party member) {
    this.member = member;
  }

  
  public Date getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess(Date lastAccess) {
    this.lastAccess = lastAccess;
  }

}
