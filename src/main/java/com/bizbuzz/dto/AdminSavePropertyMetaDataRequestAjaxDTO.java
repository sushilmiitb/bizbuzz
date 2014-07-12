package com.bizbuzz.dto;

import java.util.List;

import com.bizbuzz.model.PropertyMetadata;

public class AdminSavePropertyMetaDataRequestAjaxDTO {
  private Long categoryId;
  private List<PropertyMetadata> updatePropertyMetadata;
  private List<PropertyMetadata> newPropertyMetadata;
  
  public Long getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }
  public List<PropertyMetadata> getUpdatePropertyMetadata() {
    return updatePropertyMetadata;
  }
  public void setUpdatePropertyMetadata(
      List<PropertyMetadata> updatePropertyMetadata) {
    this.updatePropertyMetadata = updatePropertyMetadata;
  }
  public List<PropertyMetadata> getNewPropertyMetadata() {
    return newPropertyMetadata;
  }
  public void setNewPropertyMetadata(List<PropertyMetadata> newPropertyMetadata) {
    this.newPropertyMetadata = newPropertyMetadata;
  }
  
}
