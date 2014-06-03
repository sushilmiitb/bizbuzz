package com.bizbuzz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String itemNumber;
  private String styleNumber;
  
  @ManyToOne
  @JoinColumn(name="category_id")
  private CategoryTree itemCategory;

  @OneToMany(mappedBy="item")
  private List<ItemPropertyValue> properties;
  
  @ManyToMany(mappedBy="items")
  private List<Party> parties;
  
  @OneToMany(mappedBy="item")
  private List<Chat> chats;
  
  /**
   * Function handling many-to-many association between Item and PropertyMetaData
   * @param propertyMetadata
   * @param unit
   * @param value
   */
  public void addProperty(PropertyMetadata propertyMetadata, String unit, String value){
    ItemPropertyValue itemPropertyValue = new ItemPropertyValue();
    itemPropertyValue.setPropertyMetadata(propertyMetadata);
    itemPropertyValue.setPropertyMetadataId(propertyMetadata.getId());
    itemPropertyValue.setItem(this);
    itemPropertyValue.setItemId(this.getId());
    itemPropertyValue.setUnit(unit);
    itemPropertyValue.setValue(value);
    
    this.properties.add(itemPropertyValue);
    propertyMetadata.getItems().add(itemPropertyValue);
  }
  
  /**
   * getters and setters
   */
  
  public Long getId(){
    return id;
  }
  
  public String getItemNumber() {
    return itemNumber;
  }

  public void setItemNumber(String itemNumber) {
    this.itemNumber = itemNumber;
  }

  public String getStyleNumber() {
    return styleNumber;
  }

  public void setStyleNumber(String styleNumber) {
    this.styleNumber = styleNumber;
  }

  public CategoryTree getItemCategory() {
    return itemCategory;
  }

  public void setItemCategory(CategoryTree itemCategory) {
    this.itemCategory = itemCategory;
  }

  public List<ItemPropertyValue> getProperties() {
    return properties;
  }

  public void setProperties(List<ItemPropertyValue> properties) {
    this.properties = properties;
  }

  public List<Party> getParties() {
    return parties;
  }

  public void setParties(List<Party> parties) {
    this.parties = parties;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public void setChats(List<Chat> chats) {
    this.chats = chats;
  }
  
}
