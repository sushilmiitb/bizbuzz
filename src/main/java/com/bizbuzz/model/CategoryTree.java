package com.bizbuzz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="category_tree")
public class CategoryTree implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String categoryName;
  
  private Boolean isLeaf;
  
  @ManyToOne
  @JoinColumn(name="parent_category")
  private CategoryTree parentCategory;
  
  @OneToMany(mappedBy="parentCategory", fetch = FetchType.LAZY)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<CategoryTree> childrenCategory;

  @OneToMany(mappedBy="categoryRoot")
  private List<Party> referencingPartyList;
  
  @OneToMany(mappedBy="itemCategory")
  private List<Item> itemList;
  
//  @ManyToMany
//  @JoinTable(name="category_property_metadata",
//      joinColumns={@JoinColumn(name="category_id")},
//      inverseJoinColumns={@JoinColumn(name="property_metadata_id")})
//  private List<PropertyMetadata> propertyMetadatas;
  
  @OneToOne
  @JoinColumn(name="property_metadata_id", referencedColumnName="id")
  PropertyMetadata propertyMetadata;
  
  /**
   * Getters and Setters
   */

  public Boolean getIsLeaf() {
    return isLeaf;
  }

  public void setIsLeaf(Boolean isLeaf) {
    this.isLeaf = isLeaf;
  }
  
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public CategoryTree getParentCategory() {
    return parentCategory;
  }

  public void setParentCategory(CategoryTree parentCategory) {
    this.parentCategory = parentCategory;
  }

  public List<CategoryTree> getChildrenCategory() {
    return childrenCategory;
  }

  public void setChildrenCategory(List<CategoryTree> childrenCategory) {
    this.childrenCategory = childrenCategory;
  }

  public List<Party> getReferencingPartyList() {
    return referencingPartyList;
  }

  public void setReferencingPartyList(List<Party> referencingPartyList) {
    this.referencingPartyList = referencingPartyList;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }

  public PropertyMetadata getPropertyMetadata() {
    return propertyMetadata;
  }

  public void setPropertyMetadata(PropertyMetadata propertyMetadata) {
    this.propertyMetadata = propertyMetadata;
  }

  public Long getId() {
    return id;
  }
  
  

}