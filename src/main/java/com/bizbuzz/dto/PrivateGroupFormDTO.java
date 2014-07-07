package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroupFormDTO {
  private String privateGroupName;
  List<String> errors;

  public String getPrivateGroupName() {
    return privateGroupName;
  }

  public void setPrivateGroupName(String privateGroupName) {
    this.privateGroupName = privateGroupName;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }
  
}
