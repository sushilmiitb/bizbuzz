package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SellerAddPrivateGroupAjaxDTO {
  private String privateGroupName;
  private Long id;
  private String response;
  Map<String, String> errors;

  public String getPrivateGroupName() {
    return privateGroupName;
  }

  public void setPrivateGroupName(String privateGroupName) {
    this.privateGroupName = privateGroupName;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
  
}
