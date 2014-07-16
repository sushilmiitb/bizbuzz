package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SellerAddPrivateGroupResponseAjaxDTO {
  private String privateGroupName;
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
  
}