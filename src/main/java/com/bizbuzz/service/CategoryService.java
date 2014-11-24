package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;

public interface CategoryService {
  List<CategoryTree> getCategories(Long parentId);
  public CategoryTree getCategory(Long id);
  public CategoryTree saveCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf, Boolean isCustom);
  public CategoryTree saveCustomCategory(CategoryTree parentCategory, String categoryName, Person owner, Boolean isLeaf);
  public CategoryTree updateCategory(Long categoryId, String categoryName, Boolean isLeaf, Boolean isCustom);
  public void deleteCategory(Long categoryId);
  //public List<PropertyMetadata> getPropertyMetadatas(Long categoryId);
  //public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category);
  //public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas);
  
  public List<CategoryTree> getCategories(Person seller, Integer depth, Long categoryId);
  //public Map<String, Map<String, Map<String, PropertyMetadata>>> organizeMetadata(List<PropertyMetadata> properties);
  
  public CategoryTree createCategoryMap(CategoryTree categoryTree);
}
