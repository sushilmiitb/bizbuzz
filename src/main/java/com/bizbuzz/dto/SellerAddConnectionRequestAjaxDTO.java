package com.bizbuzz.dto;

public class SellerAddConnectionRequestAjaxDTO {
  private String phoneNumber;
  private Long groupId;
  
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }
}
