package com.bizbuzz.model;

import java.io.Serializable;

public class ConnectionId implements Serializable{
  private Long fromPartyId;
  private Long toPartyId;
  
  public Long getFromId() {
    return fromPartyId;
  }
  public void setFromId(Long fromId) {
    this.fromPartyId = fromId;
  }
  public Long getToId() {
    return toPartyId;
  }
  public void setToId(Long toId) {
    this.toPartyId = toId;
  }
  
  public int hashCode() {
    return (int)(fromPartyId + toPartyId);
  }
 
  public boolean equals(Object object) {
    if (object instanceof ConnectionId) {
      ConnectionId otherId = (ConnectionId) object;
      return (otherId.fromPartyId == this.fromPartyId) && (otherId.toPartyId == this.toPartyId);
    }
    return false;
  }
  
}
