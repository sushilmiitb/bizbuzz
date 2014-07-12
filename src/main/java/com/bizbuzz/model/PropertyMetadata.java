package com.bizbuzz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
  private String groupingName1;//1 is the lowest order grouping
  private String groupingName2;
  private String groupingName3;
  
  @ManyToOne
  @JoinColumn(name="property_metadata_category_id")
  private CategoryTree category;

  @OneToMany(mappedBy="propertyMetadata")
  private List<ItemPropertyValue> items;
  

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

  public List<ItemPropertyValue> getItems() {
    return items;
  }

  public void setItems(List<ItemPropertyValue> items) {
    this.items = items;
  }

  public String getGroupingName1() {
    return groupingName1;
  }

  public void setGroupingName1(String groupingName1) {
    this.groupingName1 = groupingName1;
  }

  public String getGroupingName2() {
    return groupingName2;
  }

  public void setGroupingName2(String groupingName2) {
    this.groupingName2 = groupingName2;
  }

  public String getGroupingName3() {
    return groupingName3;
  }

  public void setGroupingName3(String groupingName3) {
    this.groupingName3 = groupingName3;
  }

  public CategoryTree getCategory() {
    return category;
  }

  public void setCategory(CategoryTree category) {
    this.category = category;
  }
  
}
