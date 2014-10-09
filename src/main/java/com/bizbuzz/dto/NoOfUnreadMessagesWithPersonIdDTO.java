package com.bizbuzz.dto;

public class NoOfUnreadMessagesWithPersonIdDTO {

  private Long personId;
  private Long noOfNewMessages;
  
  public Long getPersonId() {
    return personId;
  }
  public void setPersonId(Long personId) {
    this.personId = personId;
  }
  public Long getNoOfNewMessages() {
    return noOfNewMessages;
  }
  public void setNoOfNewMessages(Long noOfNewMessages) {
    this.noOfNewMessages = noOfNewMessages;
  }
  
  
}
