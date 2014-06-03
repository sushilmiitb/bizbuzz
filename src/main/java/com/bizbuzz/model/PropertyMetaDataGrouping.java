package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PropertyMetaDataGrouping implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String groupingName;
  
  @ManyToOne
  @JoinColumn(name="parent_id")
  private PropertyMetaDataGrouping parentGrouping;

  /**
   * Setters and Getters
   */
  
  public String getGroupingName() {
    return groupingName;
  }

  public void setGroupingName(String groupingName) {
    this.groupingName = groupingName;
  }

  public PropertyMetaDataGrouping getParentGrouping() {
    return parentGrouping;
  }

  public void setParentGrouping(PropertyMetaDataGrouping parentGrouping) {
    this.parentGrouping = parentGrouping;
  }
  
}
