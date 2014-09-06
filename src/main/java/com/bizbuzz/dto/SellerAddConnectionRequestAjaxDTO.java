package com.bizbuzz.dto;

public class SellerAddConnectionRequestAjaxDTO {
  private String userId;
  private Long groupId;
  
  public String getUserId() {
    return userId;
  }

  public void setUserId(String phoneNumber) {
    this.userId = phoneNumber;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }
}
