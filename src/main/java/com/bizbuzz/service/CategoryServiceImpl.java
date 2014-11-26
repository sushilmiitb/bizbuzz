package com.bizbuzz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
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
  
  public CategoryTree saveCategory(CategoryTree parentCategory, String categoryName, Boolean isLeaf, Boolean isCustom){
    CategoryTree category = new CategoryTree();
    category.setCategoryName(categoryName);
    category.setParentCategory(parentCategory);
    category.setIsLeaf(isLeaf);
    category.setIsCustom(isCustom);
    categoryTreeRepository.save(category);
    return category;
  }
  
  public CategoryTree saveCustomCategory(CategoryTree parentCategory, String categoryName, Person owner, Boolean isLeaf){
    CategoryTree category = new CategoryTree();
    category.setCategoryName(categoryName);
    category.setParentCategory(parentCategory);
    category.setIsCustom(true);
    category.setIsLeaf(isLeaf);
    category.setOwner(owner);
    categoryTreeRepository.save(category);
    return category;
  }
  
  public CategoryTree updateCategory(Long categoryId, String categoryName, Boolean isLeaf, Boolean isCustom){
    CategoryTree category = categoryTreeRepository.findOne(categoryId);
    if(categoryName!=null){
      category.setCategoryName(categoryName);
    }
    if(isLeaf!=null){
      category.setIsLeaf(isLeaf);
    }
    if(isCustom!=null){
      category.setIsCustom(isCustom);
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
  
//  public List<PropertyMetadata> getPropertyMetadatas(Long categoryId){
//    List<PropertyMetadata> propertyMetadatas = new ArrayList<PropertyMetadata>();
//    if(categoryId == null){
//      return null;
//    }
//    propertyMetadatas = propertyMetadataRepository.findPropertyMetadataByCategoryId(categoryId);
//    return propertyMetadatas;
//  }
//  
//  public void savePropertyMetadatas(List<PropertyMetadata> propertyMetadatas, CategoryTree category){
//    for(int i=0; i<propertyMetadatas.size(); i++){
//      propertyMetadatas.get(i).setCategory(category);
//      propertyMetadataRepository.save(propertyMetadatas.get(i));
//    }
//  }
//  
//  public void updatePropertyMetadatas(List<PropertyMetadata> propertyMetadatas){
//    for(int i=0; i<propertyMetadatas.size(); i++){
//      propertyMetadataRepository.save(propertyMetadatas.get(i));
//    }
//  }
//  
  public List<CategoryTree> getCategories(Person seller, Integer depth, Long categoryId){
    CategoryTree rootCategory = seller.getCategoryRoot();
    if (rootCategory==null){
      return null;
    }
    switch (depth){
    case 1:
      return categoryTreeRepository.findCategoryDepthOne(rootCategory.getId(), categoryId);
    case 2:
      return categoryTreeRepository.findCategoryDepthTwo(rootCategory.getId(), categoryId);
    case 3:
      return categoryTreeRepository.findCategoryDepthThree(rootCategory.getId(), categoryId);
    default:
      return null;
    }
  }
  
  public CategoryTree createCategoryMap(CategoryTree categoryTree){
    if(categoryTree==null){
      return null;
    }
    List<CategoryTree> children = categoryTreeRepository.findByParentCategory(categoryTree.getId());
    for(int i=0;i<children.size();i++){
      children.set(i, createCategoryMap(children.get(i)));
    }
    return categoryTree;
  }

  public List<CategoryTree> getCategoriesByOwner(Person owner) {
        return categoryTreeRepository.findByOwner(owner);
  }

  public List<CategoryTree> getCategoriesByAdmin() {
    return categoryTreeRepository.findByIsCustom(false);
  }

//  /**
//   * This functin returns properties in form of map. Here we are assuming two level grouping of PropertyMetadata
//   * @param properties
//   * @return
//   */
//  public Map<String, Map<String, PropertyMetadata> > getPropertyMap(List<PropertyMetadata> properties){
//    String lastCategoryGrouping = "";
//    Map<String, Map<String,PropertyMetadata> > map = new HashMap<String, Map<String, PropertyMetadata>>();
//    Map<String, PropertyMetadata> tempMap = null;
//    for(int i=0;i<properties.size();i++){
//      if(i>0 && properties.get(i).getGroupingName2().equals(lastCategoryGrouping)){
//      }
//      else{
//        if(i>0){
//          map.put(properties.get(i).getGroupingName2(), tempMap);
//        }
//        lastCategoryGrouping = properties.get(i).getGroupingName2();
//        tempMap = new HashMap<String, PropertyMetadata>();
//      }
//      tempMap.put(properties.get(i).getGroupingName1(), properties.get(i));
//      if(i==properties.size()-1){
//        map.put(properties.get(i).getGroupingName2(), tempMap);
//      }
//    }
//    return map;
//  }
  
//  
//  public List<List<PropertyMetadata>> organizeMetadataList(List<PropertyMetadata> properties){
//    String lastCategoryGrouping = "";
//    List<List<PropertyMetadata>> masterList = new ArrayList<List<PropertyMetadata>>();
//    List<PropertyMetadata> tempList = null;
//    //Map<String, Map<String,PropertyMetadata> > map = new HashMap<String, Map<String, PropertyMetadata>>();
//    //Map<String, PropertyMetadata> tempMap = null;
//    for(int i=0;i<properties.size();i++){
//      if(i>0 && properties.get(i).getGroupingName2().equals(lastCategoryGrouping)){
//      }
//      else{
//        if(i>0){
//          masterList.add(tempList);
//          //map.put(properties.get(i).getGroupingName2(), tempMap);
//        }
//        lastCategoryGrouping = properties.get(i).getGroupingName2();
//        tempList = new ArrayList<PropertyMetadata>();
//        //tempMap = new HashMap<String, PropertyMetadata>();
//      }
//      tempList.add(properties.get(i));
//      //tempMap.put(properties.get(i).getGroupingName1(), properties.get(i));
//      if(i==properties.size()-1){
//        masterList.add(tempList);
//        //map.put(properties.get(i).getGroupingName2(), tempMap);
//      }
//    }
//    return masterList;
//  }
  
//  public Map<String, Map<String, Map<String, PropertyMetadata>>> organizeMetadata(List<PropertyMetadata> properties){
//    Map<String, Map<String, Map<String, PropertyMetadata>>> masterMap = new HashMap<String, Map<String, Map<String, PropertyMetadata>>>();
//    String lastGroupingCode2="";
//    String lastGroupingCode1="";
//    Map<String, Map<String,PropertyMetadata>> highlevelMap = new HashMap<String, Map<String, PropertyMetadata>>();
//    Map<String, PropertyMetadata> lowlevelMap = new HashMap<String, PropertyMetadata>();
//    for(int i=0;i<properties.size();i++){
//      String currentGroupingCode2 = properties.get(i).getGroupingCode2();
//      String currentGroupingCode1 = properties.get(i).getGroupingCode1();
//      if(currentGroupingCode1!=lastGroupingCode1 && i>0){
//        highlevelMap.put(currentGroupingCode1, lowlevelMap);
//        lastGroupingCode1 = currentGroupingCode1;
//        lowlevelMap = new HashMap<String, PropertyMetadata>();
//      }
//      
//      if(currentGroupingCode2!=lastGroupingCode2 && i>0){
//        masterMap.put(lastGroupingCode2, highlevelMap);
//        lastGroupingCode2 = currentGroupingCode2;
//        highlevelMap = new HashMap<String, Map<String, PropertyMetadata>>();
//      }
//      lowlevelMap.put(properties.get(i).getPropertyCode(), properties.get(i));
//      
//      if(i==properties.size()-1){
//        highlevelMap.put(currentGroupingCode1, lowlevelMap);
//        masterMap.put(currentGroupingCode2, highlevelMap);
//      }
//    }
//    return masterMap;
//  }
  
  
}
