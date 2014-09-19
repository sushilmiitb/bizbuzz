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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class PropertyField implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  private Long id; 
  
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name="property_sub_group_id", referencedColumnName="id")
  private PropertySubGroup propertySubGroup;
  
  @OneToMany(mappedBy="propertyField", fetch=FetchType.LAZY)
  private List<PropertyValue> propertyValues;
  
  private String tag="";
  private String value="";
  
  private Date createdAt;
  private Date updatedAt;

  public PropertySubGroup getPropertySubGroup() {
    return propertySubGroup;
  }

  public void setPropertySubGroup(PropertySubGroup propertySubGroup) {
    this.propertySubGroup = propertySubGroup;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = new Date();
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public List<PropertyValue> getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(List<PropertyValue> propertyValues) {
    this.propertyValues = propertyValues;
  }

  
}
