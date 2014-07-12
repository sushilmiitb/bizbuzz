package com.bizbuzz.dto;

public class AdminAddCategoryResponseAjaxDTO {
  private String categoryName;
  private Long categoryId;
  
  public AdminAddCategoryResponseAjaxDTO(){
    
  }
  
  public AdminAddCategoryResponseAjaxDTO(String categoryName, Long categoryId){
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }
  
}
