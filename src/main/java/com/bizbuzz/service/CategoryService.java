package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;

public interface CategoryService {
  List<CategoryTree> getCategories(Long parentId);
  public CategoryTree getCategory(Long id);
  public CategoryTree saveCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf);
  public CategoryTree updateCategory(Long categoryId, String categoryName, Boolean isLeaf);
  public void deleteCategory(Long categoryId);
  //public List<PropertyMetadata> getPropertyMetadatas(Long categoryId);
  //public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category);
  //public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas);
  //public List<PropertyMetadata> getPropertyMetadatas(Person seller, Integer depth, Long categoryId);
  public List<CategoryTree> getCategories(Person seller, Integer depth, Long categoryId);
  //public Map<String, Map<String, Map<String, PropertyMetadata>>> organizeMetadata(List<PropertyMetadata> properties);
  public PropertyMetadata getPropertyMetadata(Long categoryId);
  public PropertyMetadata savePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId);
  public PropertyMetadata saveExistingPropertyMetadata(Long propertyMetadataId, Long categoryId);
}
