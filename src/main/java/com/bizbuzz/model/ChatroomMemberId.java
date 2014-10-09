package com.bizbuzz.model;

import java.io.Serializable;

public class ChatroomMemberId implements Serializable{

  private Long chatroomId;
  private Long memberId;
  
  public Long getChatroomId() {
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
 
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((chatroomId == null) ? 0 : chatroomId.hashCode());
    result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ChatroomMemberId other = (ChatroomMemberId) obj;
    if (chatroomId == null) {
      if (other.chatroomId != null)
        return false;
    } else if (!chatroomId.equals(other.chatroomId))
      return false;
    if (memberId == null) {
      if (other.memberId != null)
        return false;
    } else if (!memberId.equals(other.memberId))
      return false;
    return true;
  }
  
  
  
  
}
