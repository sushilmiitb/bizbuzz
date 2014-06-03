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
public class PropertyMetadata implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String propertyName;
  private String possibleUnits;//in form of comma separated words
  private String possibleValues;//in form of comma separated words
  
  @ManyToOne
  @JoinColumn(name="parent_id")
  private PropertyMetaDataGrouping parentGrouping;

  @OneToMany(mappedBy="propertyMetadata")
  private List<ItemPropertyValue> items;
  
  @ManyToMany(mappedBy="properties")
  private List<CategoryTree> categories;

  /**
   * Setters and Getters
   */
  public Long getId(){
    return id;
  }
  
  public String getPropertyName() {
    return propertyName;
  }

  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getPossibleUnits() {
    return possibleUnits;
  }

  public void setPossibleUnits(String possibleUnits) {
    this.possibleUnits = possibleUnits;
  }

  public String getPossibleValues() {
    return possibleValues;
  }

  public void setPossibleValues(String possibleValues) {
    this.possibleValues = possibleValues;
  }

  public PropertyMetaDataGrouping getParentGrouping() {
    return parentGrouping;
  }

  public void setParentGrouping(PropertyMetaDataGrouping parentGrouping) {
    this.parentGrouping = parentGrouping;
  }

  public List<ItemPropertyValue> getItems() {
    return items;
  }

  public void setItems(List<ItemPropertyValue> items) {
    this.items = items;
  }

  public List<CategoryTree> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryTree> categories) {
    this.categories = categories;
  }
  
}
