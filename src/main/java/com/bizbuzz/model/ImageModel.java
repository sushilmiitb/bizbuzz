package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class ImageModel implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  
  private String originalFilename;
  private String tag;
  
  @OneToOne(mappedBy="primaryImageModel")
  private Property primaryImageProperty;
  @OneToOne(mappedBy="image1Model")
  private Property image1Property;
  @OneToOne(mappedBy="image2Model")
  private Property image2Property;
  @OneToOne(mappedBy="image3Model")
  private Property image3Property;
  
  
  private Date created;
  private Date updated;

  public String getFilename(){
    return id.toString();
  }
  
  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getOriginalFilename() {
    return originalFilename;
  }

  public void setOriginalFilename(String originalFilename) {
    this.originalFilename = originalFilename;
  }

  public Long getId() {
    return id;
  }
  
  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }

  public Property getPrimaryImageProperty() {
    return primaryImageProperty;
  }

  public void setPrimaryImageProperty(Property primaryImageProperty) {
    this.primaryImageProperty = primaryImageProperty;
  }

  public Property getImage1Property() {
    return image1Property;
  }

  public void setImage1Property(Property image1Property) {
    this.image1Property = image1Property;
  }

  public Property getImage2Property() {
    return image2Property;
  }

  public void setImage2Property(Property image2Property) {
    this.image2Property = image2Property;
  }

  public Property getImage3Property() {
    return image3Property;
  }

  public void setImage3Property(Property image3Property) {
    this.image3Property = image3Property;
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
