package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SellerAddCategoryResponseAjaxDTO {
  private String categoryName;
  private Long categoryId;
  private String response;
  Map<String, String> errors;

  
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }
  
  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
  
}
