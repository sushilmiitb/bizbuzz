package com.bizbuzz.dto;

public class GeneralAjaxResponseDTO {
  private String message;
  private Long id;
  
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  
}
