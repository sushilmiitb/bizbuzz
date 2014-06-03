package com.bizbuzz.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@IdClass(ItemPropertyValueId.class)
public class ItemPropertyValue{
  private static final long serialVersionUID = 1L;
  @Id
  private Long itemId;
  @Id
  private Long propertyMetadataId;
  
  @ManyToOne
  @PrimaryKeyJoinColumn(name="item_id", referencedColumnName="id")
  private Item item;
  
  @ManyToOne
  @PrimaryKeyJoinColumn(name="property_metadata_id", referencedColumnName="id")
  private PropertyMetadata propertyMetadata;
  
  private String unit;
  private String value;
  
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
  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }
  public PropertyMetadata getPropertyMetadata() {
    return propertyMetadata;
  }
  public void setPropertyMetadata(PropertyMetadata propertyMetadata) {
    this.propertyMetadata = propertyMetadata;
  }
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  
}
