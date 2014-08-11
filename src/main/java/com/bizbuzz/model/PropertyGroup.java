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
public class PropertyGroup implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  private Long id;
  
  private String tag="";
  private String name="";
  
  @OneToMany(mappedBy="propertyGroup", fetch=FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
  @Fetch(value=FetchMode.SUBSELECT)
  private List<PropertySubGroup> propertySubGroups = new ArrayList<PropertySubGroup>();
  
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name="property_id", referencedColumnName="id")
  private Property property;
  
  public void setPropertySubGroups(int size){
    this.propertySubGroups = new ArrayList<PropertySubGroup>(size);
    for(int i=0; i<size; i++){
      propertySubGroups.add(new PropertySubGroup());
    }
  }
  
  public String getName() {
    return name;
  }

  public void setName(String propertyGroupName) {
    this.name = propertyGroupName;
  }

  public List<PropertySubGroup> getPropertySubGroups() {
    return propertySubGroups;
  }

  public void setPropertySubGroups(List<PropertySubGroup> propertySubGroups) {
    this.propertySubGroups.clear();
    this.propertySubGroups.addAll(propertySubGroups);
  }

  public Property getProperty() {
    return property;
  }

  public void setProperty(Property property) {
    this.property = property;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

}
