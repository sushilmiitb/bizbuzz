package com.bizbuzz.model;

import java.io.Serializable;

public class ItemPropertyValueId implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long itemId;
  private Long propertyMetadataId;
  
  /**
   * setters and getters
   */
  
  public Long getItemId() {
    return itemId;
  }
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  public Long getPropertyMetadataId() {
    return propertyMetadataId;
  }
  public void setPropertyMetadataId(Long propertyMetadataId) {
    this.propertyMetadataId = propertyMetadataId;
  }
  
}
