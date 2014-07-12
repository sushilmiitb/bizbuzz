package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.PropertyMetadata;

public interface CategoryService {
  List<CategoryTree> getCategories(Long parentId);
  public CategoryTree getCategory(Long id);
  public CategoryTree saveCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf);
  public CategoryTree updateCategory(Long categoryId, String categoryName, Boolean isLeaf);
  public void deleteCategory(Long categoryId);
  public List<PropertyMetadata> getPropertyMetadatas(Long categoryId);
  public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category);
  public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas);
}
