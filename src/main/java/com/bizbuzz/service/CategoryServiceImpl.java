package com.bizbuzz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.repository.CategoryTreeRepository;
import com.bizbuzz.repository.PropertyMetadataRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
  @Autowired
  CategoryTreeRepository categoryTreeRepository;
  
  @Autowired
  PropertyMetadataRepository propertyMetadataRepository;

  public List<CategoryTree> getCategories(Long parentId) {
    return categoryTreeRepository.findByParentCategory(parentId);
  }

  public CategoryTree getCategory(Long id){
    return categoryTreeRepository.findOne(id);
  }
  
  public CategoryTree saveCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf){
    CategoryTree category = new CategoryTree();
    category.setCategoryName(categoryName);
    category.setParentCategory(parentCategory);
    category.setIsLeaf(isLeaf);
    categoryTreeRepository.save(category);
    return category;
  }
  
  public CategoryTree updateCategory(Long categoryId, String categoryName, Boolean isLeaf){
    CategoryTree category = categoryTreeRepository.findOne(categoryId);
    if(categoryName!=null){
      category.setCategoryName(categoryName);
    }
    if(isLeaf!=null){
      category.setIsLeaf(isLeaf);
    }
    categoryTreeRepository.save(category);
    return category;
  }
  
  public void deleteCategory(Long categoryId){
    if(categoryId==null){
      return;
    }
    List<CategoryTree> children = categoryTreeRepository.findByParentCategory(categoryId);
    for(int i=0; i<children.size();i++){
      deleteCategory(children.get(i).getId());
    }
    categoryTreeRepository.delete(categoryId);
  }
  
  public List<PropertyMetadata> getPropertyMetadatas(Long categoryId){
    List<PropertyMetadata> propertyMetadatas = new ArrayList<PropertyMetadata>();
    if(categoryId == null){
      return null;
    }
    propertyMetadatas = propertyMetadataRepository.findPropertyMetadataByCategoryId(categoryId);
    return propertyMetadatas;
  }
  
  public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category){
    for(int i=0; i<propertyMetadatas.size(); i++){
      propertyMetadatas.get(i).setCategory(category);
      propertyMetadataRepository.save(propertyMetadatas.get(i));
    }
  }
  
  public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas){
    for(int i=0; i<propertyMetadatas.size(); i++){
      propertyMetadataRepository.save(propertyMetadatas.get(i));
    }
  }
  
}
