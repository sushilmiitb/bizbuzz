package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;

public interface CategoryService {
  public List<CategoryTree> getAdminCategories(Long parentId);
  public List<CategoryTree> getCustomCategories(Long parentId, Long ownerId);
  public List<CategoryTree> getAllCategories(Long parentId, Long ownerId);
  public CategoryTree getCategory(Long id);
  public CategoryTree saveAdminCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf, Boolean hasProduct);
  public CategoryTree saveCustomCategory(CategoryTree parentCategory, String categoryName, Person owner, Boolean hasProduct, Boolean isLeaf);
  public CategoryTree updateAdminCategory(Long categoryId, String categoryName, Boolean isLeaf,  Boolean hasProduct);
  public CategoryTree updateCustomCategory(Long categoryId, String categoryName, Boolean isLeaf, Boolean hasProduct);
  public void deleteCategory(Long categoryId);
  //public List<PropertyMetadata> getPropertyMetadatas(Long categoryId);
  //public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category);
  //public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas);
  
  public List<CategoryTree> getCategories(Person seller, Integer depth, Long categoryId);
  //public Map<String, Map<String, Map<String, PropertyMetadata>>> organizeMetadata(List<PropertyMetadata> properties);
  
  public CategoryTree createCategoryMap(CategoryTree categoryTree);
  public CategoryTree getCategoryThatHasNearestMetadata(CategoryTree categoryTree);
}
