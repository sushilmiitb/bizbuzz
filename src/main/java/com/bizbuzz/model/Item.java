package com.bizbuzz.model;

import java.io.Serializable;


import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



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

  
  @ManyToMany(fetch = FetchType.LAZY)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
      name="share",
      joinColumns={@JoinColumn(name="item_id", referencedColumnName="id")},
          inverseJoinColumns={@JoinColumn(name="party_id", referencedColumnName="id")})
  private List<Party> sharedToParties;
 
  @OneToMany(mappedBy="item", fetch = FetchType.LAZY)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Chat> chats;
 
  @ManyToOne
  @JoinColumn(name="owner_id", referencedColumnName="id")
  private Party owner;
  
  @OneToMany(mappedBy="item", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
  @Fetch(value=FetchMode.SUBSELECT)
  private List<PropertyValue> propertyValues;
    
  @OneToMany(mappedBy="item", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
  @Fetch(value=FetchMode.SUBSELECT)
  private List<ImageModel> imageModels;
  
  private Date created;
  private Date updated;
  
  /**
   * Function handling many-to-many association between Item and PropertyMetaData
   * @param propertyMetadata
   * @param unit
   * @param value
   */
//  public void addProperty(PropertyMetadata propertyMetadata, String unit, String value){
//    ItemPropertyValue itemPropertyValue = new ItemPropertyValue();
//    itemPropertyValue.setPropertyMetadata(propertyMetadata);
//    itemPropertyValue.setPropertyMetadataId(propertyMetadata.getId());
//    itemPropertyValue.setItem(this);
//    itemPropertyValue.setItemId(this.getId());
//    itemPropertyValue.setUnit(unit);
//    itemPropertyValue.setValue(value);
//    
//    this.properties.add(itemPropertyValue);
//    propertyMetadata.getItems().add(itemPropertyValue);
//  }
  
  public ImageModel getImageModelByTag(String tag){
    if(imageModels == null){
      return null;
    }
    for(int i=0; i<imageModels.size(); i++){
      if(imageModels.get(i).getTag()==tag){
        return imageModels.get(i);
      }
    }
    return null;
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

  public List<Party> getSharedToParties() {
    return sharedToParties;
  }

  public void setSharedToParties(List<Party> sharedToParties) {
    this.sharedToParties = sharedToParties;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public void setChats(List<Chat> chats) {
    this.chats = chats;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<PropertyValue> getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(List<PropertyValue> propertyValue) {
    this.propertyValues = propertyValue;
  }
  
  public Party getOwner() {
    return owner;
  }

  public void setOwner(Party owner) {
    this.owner = owner;
  }

  public List<ImageModel> getImageModels() {
    return imageModels;
  }

  public void setImageModels(List<ImageModel> imageModels) {
    this.imageModels = imageModels;
  }
  
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @PrePersist
  protected void onCreate() {
    created = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated = new Date();
  }
}
