//package com.bizbuzz.model;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//@Entity
//public class PropertyMetadata implements Serializable{
//  private static final long serialVersionUID = 1L;
//  
//  @Id
//  @GeneratedValue
//  private Long id;
//  
//  private String propertyName;
//  private String possibleUnits;//in form of comma separated words
//  private String possibleValues;//in form of comma separated words
//  private String groupingName1;//1 is the lowest order grouping
//  private String groupingName2;
//  
//  /**
//   *  Code values will be used to position the elements on UI.
//   *  (groupingcode2,groupingCode1,propertyCode) should be unique
//   */
//  private String propertyCode;
//  private String groupingCode1;
//  private String groupingCode2;
//  
//  @ManyToOne
//  @JoinColumn(name="property_metadata_category_id")
//  private CategoryTree category;
//
//  @OneToMany(mappedBy="propertyMetadata")
//  private List<ItemPropertyValue> items;
//  
//
//  /**
//   * Setters and Getters
//   */
//  public Long getId(){
//    return id;
//  }
//  
//  public String getPropertyName() {
//    return propertyName;
//  }
//
//  public void setPropertyName(String propertyName) {
//    this.propertyName = propertyName;
//  }
//
//  public String getPossibleUnits() {
//    return possibleUnits;
//  }
//
//  public void setPossibleUnits(String possibleUnits) {
//    this.possibleUnits = possibleUnits;
//  }
//
//  public String getPossibleValues() {
//    return possibleValues;
//  }
//
//  public void setPossibleValues(String possibleValues) {
//    this.possibleValues = possibleValues;
//  }
//
//  public List<ItemPropertyValue> getItems() {
//    return items;
//  }
//
//  public void setItems(List<ItemPropertyValue> items) {
//    this.items = items;
//  }
//
//  public String getGroupingName1() {
//    return groupingName1;
//  }
//
//  public void setGroupingName1(String groupingName1) {
//    this.groupingName1 = groupingName1;
//  }
//
//  public String getGroupingName2() {
//    return groupingName2;
//  }
//
//  public void setGroupingName2(String groupingName2) {
//    this.groupingName2 = groupingName2;
//  }
//
//
//  public CategoryTree getCategory() {
//    return category;
//  }
//
//  public void setCategory(CategoryTree category) {
//    this.category = category;
//  }
//
//  public String getPropertyCode() {
//    return propertyCode;
//  }
//
//  public void setPropertyCode(String propertyCode) {
//    this.propertyCode = propertyCode;
//  }
//
//  public String getGroupingCode1() {
//    return groupingCode1;
//  }
//
//  public void setGroupingCode1(String groupingCode1) {
//    this.groupingCode1 = groupingCode1;
//  }
//
//  public String getGroupingCode2() {
//    return groupingCode2;
//  }
//
//  public void setGroupingCode2(String groupingCode2) {
//    this.groupingCode2 = groupingCode2;
//  }
//  
//}

package com.bizbuzz.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("metadata")
public class PropertyMetadata extends Property{
  private static final long serialVersionUID = 1L;
  
  @OneToOne(mappedBy="propertyMetadata")
  private CategoryTree category;

  public CategoryTree getCategory() {
    return category;
  }

  public void setCategory(CategoryTree category) {
    this.category = category;
  }
  
}