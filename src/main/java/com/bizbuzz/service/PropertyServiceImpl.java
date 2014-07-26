package com.bizbuzz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.repository.CategoryTreeRepository;
import com.bizbuzz.repository.ImageModelRepository;
import com.bizbuzz.repository.PropertyMetadataRepository;
import com.bizbuzz.repository.PropertyValueRepository;
import com.bizbuzz.utils.HelperFunctions;

@Service
public class PropertyServiceImpl implements PropertyService{
  @Autowired
  PropertyMetadataRepository propertyMetadataRepository;
  @Autowired
  CategoryTreeRepository categoryTreeRepository;
  @Autowired
  PropertyValueRepository propertyValueRepository;
  @Autowired
  ImageModelRepository imageModelRepository;
  
     
  public PropertyMetadata getPropertyMetadata(Person seller, Integer depth, Long categoryId){
    CategoryTree rootCategory = seller.getCategoryRoot();
    if(rootCategory==null){
      return null;
    }
    switch(depth){
    case 1:
      return propertyMetadataRepository.findCategoryDepthOne(rootCategory.getId(), categoryId);
    case 2:
      return propertyMetadataRepository.findCategoryDepthTwo(rootCategory.getId(), categoryId);
    case 3:
      return propertyMetadataRepository.findCategoryDepthThree(rootCategory.getId(), categoryId);
    default:
      return null;
    }
  }

  public PropertyMetadata getPropertyMetadata(Long categoryId){
    return propertyMetadataRepository.findPropertyMetadataByCategoryId(categoryId);
  }
  
  public PropertyMetadata savePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId){
    CategoryTree categoryTree = categoryTreeRepository.findOne(categoryId);
    propertyMetadata = propertyMetadataRepository.save(propertyMetadata);
    categoryTree.setPropertyMetadata(propertyMetadata);
    categoryTreeRepository.save(categoryTree);
    return propertyMetadata;
  }
  
  public PropertyMetadata saveExistingPropertyMetadata(Long propertyMetadataId, Long categoryId){
    CategoryTree categoryTree = categoryTreeRepository.findOne(categoryId);
    PropertyMetadata propertyMetadata = propertyMetadataRepository.findOne(propertyMetadataId);
    categoryTree.setPropertyMetadata(propertyMetadata);
    categoryTreeRepository.save(categoryTree);
    return propertyMetadata;
  }

  public PropertyValue savePropertyValue(PropertyValue propertyValue){
    return propertyValueRepository.save(propertyValue);
  }
  
  public void getPropertyValue(Long propertyValueId){
    propertyValueRepository.findOne(propertyValueId);
  }
  
  public ImageModel saveImage(MultipartFile image, PropertyValue propertyValue, String tag){
    ImageModel imageModel = null;
    if(image!=null && !image.isEmpty()){
      imageModel = new ImageModel();
      imageModel.setOriginalFilename(image.getOriginalFilename());
      imageModel.setTag(tag);
      imageModel = imageModelRepository.save(imageModel);
      
      byte[] bytes = null;
      try {
        bytes = image.getBytes();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      HelperFunctions.saveAllSizeImages(imageModel.getId().toString(), bytes);
    }
    return imageModel;
  }
  
  public ImageModel saveByteImage(byte[] image, String tag){
    ImageModel imageModel = null;
    if(image!=null){
      imageModel = new ImageModel();
      //imageModel.setOriginalFilename(image.getOriginalFilename());
      imageModel.setTag(tag);
      imageModel = imageModelRepository.save(imageModel);
      HelperFunctions.saveAllSizeImages(imageModel.getId().toString(), image);
    }
    return imageModel;
  }
  
  /***
   * As a convention this function saves the images in order in which it appears in the model.
   */
  public List<ImageModel> saveImagesInOrder(List<MultipartFile> images, PropertyValue propertyValue){
    List<String> tags = propertyValue.getImageTags();
    List<ImageModel> imageModels = new ArrayList<ImageModel>(images.size());
    for(int i=0; i<images.size(); i++){
      imageModels.add(i, saveImage(images.get(i), propertyValue, tags.get(i)));
    }
    return imageModels;
  }
  
  public List<ImageModel> saveByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue){
    List<String> tags = propertyValue.getImageTags();
    List<ImageModel> imageModels = new ArrayList<ImageModel>(images.size());
    for(int i=0; i<images.size(); i++){
      imageModels.add(i, saveByteImage(images.get(i), tags.get(i)));
    }
    return imageModels;
  }
  
  public ImageModel updateByteImage(byte[] image, ImageModel imageModel, String tag){
    if(image!=null){
      //imageModel.setOriginalFilename(image.getOriginalFilename());
      imageModel.setTag(tag);
      imageModel = imageModelRepository.save(imageModel);
      HelperFunctions.saveAllSizeImages(imageModel.getId().toString(), image);
    }
    return imageModel;
  }
  
  public List<ImageModel> updateByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue){
    List<String> tags = propertyValue.getImageTags();
    List<ImageModel> imageModels = propertyValue.getImageModelsInOrder();
    for(int i=0; i<images.size(); i++){
      if(imageModels.get(i)==null){
        imageModels.set(i, saveByteImage(images.get(i), tags.get(i)));
      }
      else{
        imageModels.set(i, updateByteImage(images.get(i), imageModels.get(i), tags.get(i)));
      }
    }
    return imageModels;
  }
  
  public PropertyValue updatePropertyValue(PropertyValue newPropertyValue, PropertyValue oldPropertyValue){
    newPropertyValue.setId(oldPropertyValue.getId());
    newPropertyValue.setImageModelsInOrder(oldPropertyValue.getImageModelsInOrder());
    return newPropertyValue;
  }
  
  public String getImageDir(){
    return HelperFunctions.getImageDir(getClass().getResourceAsStream("/application/AppConstants.xml"));
  }
  
//  public List<PropertyValue> getPropertyValueListByOwnerIdAndCategoryId(Long ownerId, Long categoryId){
//    
//  }
  
}
