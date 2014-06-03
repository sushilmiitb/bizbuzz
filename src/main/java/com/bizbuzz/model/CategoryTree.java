package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category_tree")
public class CategoryTree implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String categoryName;
  
  @ManyToOne
  @JoinColumn(name="parent_category")
  private CategoryTree parentCategory;
  
  @OneToMany(mappedBy="parentCategory")
  private List<CategoryTree> childrenCategory;

  @OneToMany(mappedBy="categoryRoot")
  private List<Party> referencingPartyList;
  
  @OneToMany(mappedBy="itemCategory")
  private List<Item> itemList;
  
  @ManyToMany
  @JoinTable(
      name="category_property_metadata",
      joinColumns={@JoinColumn(name="category_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="metadata_id", referencedColumnName="id")})
  private List<PropertyMetadata> properties;
  
  /**
   * Getters and Setters
   */
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

  public List<PropertyMetadata> getProperties() {
    return properties;
  }

  public void setProperties(List<PropertyMetadata> properties) {
    this.properties = properties;
  }
  
}
