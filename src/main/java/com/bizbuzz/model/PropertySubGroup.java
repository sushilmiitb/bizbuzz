package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class PropertySubGroup implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  private Long id; 
  
  @OneToMany(mappedBy="propertySubGroup", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
  @Fetch(value=FetchMode.SUBSELECT)
  private List<PropertyField> propertyFields = new ArrayList<PropertyField>();
  
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name="property_group_id", referencedColumnName="id")
  private PropertyGroup propertyGroup;

  private String tag="";
  
  private String name="";
  
  public void setPropertyFields(int size){
    this.propertyFields = new ArrayList<PropertyField>(size);
    for(int i=0; i<size; i++){
      propertyFields.add(new PropertyField());
    }
  }
  
  public List<PropertyField> getPropertyFields() {
    return propertyFields;
  }

  public void setPropertyFields(List<PropertyField> propertyFields) {
    this.propertyFields.clear();
    this.propertyFields.addAll(propertyFields);
  }

  public String getName() {
    return name;
  }

  public void setName(String subGroupName) {
    this.name = subGroupName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PropertyGroup getPropertyGroup() {
    return propertyGroup;
  }

  public void setPropertyGroup(PropertyGroup propertyGroup) {
    this.propertyGroup = propertyGroup;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
  
  
  
}