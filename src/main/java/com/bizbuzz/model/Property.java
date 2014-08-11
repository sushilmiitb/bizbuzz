package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * 
 * @author sushil
 * Any Changes in this file require changes in dto.ProductDetailDTO, seller/viewproductupload.jsp, ItemService/saveFiles,
 * PropertyValue (for file changes), 
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="property_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Property implements Serializable{
  private static final long serialVersionUID = 4009473233597932062L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  
  @OneToMany(mappedBy="property", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
  @Fetch(value=FetchMode.SUBSELECT)
  private List<ImageModel> imageModels = new ArrayList<ImageModel>();
  
  @OneToMany(mappedBy="property", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
  @Fetch(value=FetchMode.SUBSELECT)
  private List<PropertyGroup> propertyGroups = new ArrayList<PropertyGroup>();
  
  private Date createdAt;
  
  private Date updatedAt;
  
  public void initialize(int imageModelsSize, int propertyGroupsSize){
    imageModels = new ArrayList<ImageModel>(imageModelsSize);
    for(int i=0; i<imageModelsSize; i++){
      imageModels.add(new ImageModel());
    }
    propertyGroups = new ArrayList<PropertyGroup>(propertyGroupsSize);
    for(int i=0; i<propertyGroupsSize; i++){
      propertyGroups.add(new PropertyGroup());
    }
  }
  
  public List<String> getImageTags(){
    List<String> tags = new ArrayList<String>();
    for(int i=0; i<imageModels.size();i++){
      tags.add(i, imageModels.get(i).getTag());
    }
    return tags;
  }
  
  public void setImageModelsInOrder(List<ImageModel> imageModels){
    
  }
  
  public List<ImageModel> getImageModelsInOrder(){
    List<ImageModel> imageModels = new ArrayList<ImageModel>();
    
    return imageModels;
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }
  public Date getCreatedAt() {
    return createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public List<ImageModel> getImageModels() {
    return imageModels;
  }

  public void setImageModels(List<ImageModel> imageModels) {
    this.imageModels.clear();
    this.imageModels.addAll(imageModels);
  }

  public List<PropertyGroup> getPropertyGroups() {
    return propertyGroups;
  }

  public void setPropertyGroups(List<PropertyGroup> propertyGroups) {
    this.propertyGroups.clear();
    this.propertyGroups.addAll(propertyGroups);
  }

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }
  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }
    
}
