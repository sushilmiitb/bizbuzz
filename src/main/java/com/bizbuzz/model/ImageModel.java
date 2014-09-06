package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  private Long id = null;
  
  private String originalFilename = "";
  private String tag = "";
  private String name = "";
  
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name="property_id", referencedColumnName="id")
  private PropertyMetadata propertyMetadata;
  
  @ManyToOne
  @JoinColumn(name="image_model_metadata_id", referencedColumnName="id")
  private ImageModel imageModelMetadata;
  
  @OneToMany(mappedBy="imageModelMetadata", fetch=FetchType.LAZY)
  private List<ImageModel> imageModelValues;
  
  @ManyToOne
  @JoinColumn(name="item_id", referencedColumnName="id")
  private Item item;
  
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
  
  public PropertyMetadata getProperty() {
    return propertyMetadata;
  }

  public void setProperty(PropertyMetadata propertyMetadata) {
    this.propertyMetadata = propertyMetadata;
  }

  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PropertyMetadata getPropertyMetadata() {
    return propertyMetadata;
  }

  public void setPropertyMetadata(PropertyMetadata propertyMetadata) {
    this.propertyMetadata = propertyMetadata;
  }

  public ImageModel getImageModelMetadata() {
    return imageModelMetadata;
  }

  public void setImageModelMetadata(ImageModel imageModelMetadata) {
    this.imageModelMetadata = imageModelMetadata;
  }

  public List<ImageModel> getImageModelValues() {
    return imageModelValues;
  }

  public void setImageModelValues(List<ImageModel> imageModelValues) {
    this.imageModelValues = imageModelValues;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
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
