package com.bizbuzz.service;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyField;
import com.bizbuzz.model.PropertyGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertySubGroup;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.repository.CategoryTreeRepository;
import com.bizbuzz.repository.ImageModelRepository;
import com.bizbuzz.repository.PropertyFieldRepository;
import com.bizbuzz.repository.PropertyGroupRepository;
import com.bizbuzz.repository.PropertyMetadataRepository;
import com.bizbuzz.repository.PropertySubGroupRepository;
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
  @Autowired
  PropertyFieldRepository propertyFieldRepository;
  @Autowired
  PropertySubGroupRepository propertySubGroupRepository;
  @Autowired
  PropertyGroupRepository propertyGroupRepository;



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

  @Transactional
  public PropertyMetadata getPropertyMetadata(Long categoryId){
    PropertyMetadata pm = propertyMetadataRepository.findPropertyMetadataByCategoryId(categoryId);
    Hibernate.initialize(pm.getImageModels().size());
    Hibernate.initialize(pm.getPropertyGroups());
    return pm;
  }

  public PropertyMetadata savePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId){
    CategoryTree categoryTree = categoryTreeRepository.findOne(categoryId);
    propertyMetadata = propertyMetadataRepository.save(propertyMetadata);
    List<ImageModel> imageModels = propertyMetadata.getImageModels();
    for(int i=0; i<imageModels.size(); i++){
      imageModels.get(i).setProperty(propertyMetadata);
    }
    List<PropertyGroup> groups = propertyMetadata.getPropertyGroups();
    for(int i=0; (groups!=null && i<groups.size()); i++){
      groups.get(i).setProperty(propertyMetadata);
      List<PropertySubGroup> subgroups = groups.get(i).getPropertySubGroups();
      for(int j=0; (subgroups!=null && j<subgroups.size()); j++){
        subgroups.get(j).setPropertyGroup(groups.get(i));
        List<PropertyField> fields = subgroups.get(j).getPropertyFields();
        for(int k=0; (fields!=null && k<fields.size()); k++){
          fields.get(k).setPropertySubGroup(subgroups.get(j));
        }
      }
    }
    propertyMetadataRepository.save(propertyMetadata);
    categoryTree.setPropertyMetadata(propertyMetadata);
    categoryTreeRepository.save(categoryTree);
    return propertyMetadata;
  }

  public PropertyMetadata updatePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId){
    PropertyMetadata oldMetadata = propertyMetadataRepository.findPropertyMetadataByCategoryId(categoryId);
    propertyMetadata.setId(oldMetadata.getId());

    List<ImageModel> imageModels = oldMetadata.getImageModels();
    for(int i=0; i<imageModels.size(); i++){
      propertyMetadata.getImageModels().get(i).setId(imageModels.get(i).getId());
    }

    List<PropertyGroup> groups = oldMetadata.getPropertyGroups();
    for(int i=0; (groups!=null && i<groups.size()); i++){
      propertyMetadata.getPropertyGroups().get(i).setId(groups.get(i).getId());
      List<PropertySubGroup> subgroups = groups.get(i).getPropertySubGroups();
      for(int j=0; (subgroups!=null && j<subgroups.size()); j++){
        propertyMetadata.getPropertyGroups().get(i).getPropertySubGroups().get(j).setId(subgroups.get(j).getId());
        List<PropertyField> fields = subgroups.get(j).getPropertyFields();
        for(int k=0; (fields!=null && k<fields.size()); k++){
          propertyMetadata.getPropertyGroups().get(i).getPropertySubGroups().get(j).getPropertyFields().get(k).setId(fields.get(k).getId());
        }
      }
    }

    propertyMetadata = propertyMetadataRepository.save(propertyMetadata);
    imageModels = propertyMetadata.getImageModels();
    for(int i=0; i<imageModels.size(); i++){
      imageModels.get(i).setProperty(propertyMetadata);
    }
    groups = propertyMetadata.getPropertyGroups();
    for(int i=0; (groups!=null && i<groups.size()); i++){
      groups.get(i).setProperty(propertyMetadata);
      List<PropertySubGroup> subgroups = groups.get(i).getPropertySubGroups();
      for(int j=0; (subgroups!=null && j<subgroups.size()); j++){
        subgroups.get(j).setPropertyGroup(groups.get(i));
        List<PropertyField> fields = subgroups.get(j).getPropertyFields();
        for(int k=0; (fields!=null && k<fields.size()); k++){
          fields.get(k).setPropertySubGroup(subgroups.get(j));
        }
      }
    }
    propertyMetadataRepository.save(propertyMetadata);
    return propertyMetadata;
  }



  //  public PropertyMetadata saveExistingPropertyMetadata(Long propertyMetadataId, Long categoryId){
  //    CategoryTree categoryTree = categoryTreeRepository.findOne(categoryId);
  //    PropertyMetadata propertyMetadata = propertyMetadataRepository.findOne(propertyMetadataId);
  //    categoryTree.setPropertyMetadata(propertyMetadata);
  //    categoryTreeRepository.save(categoryTree);
  //    return propertyMetadata;
  //  }

  public PropertyValue savePropertyValue(PropertyValue propertyValue){
    return propertyValueRepository.save(propertyValue);
  }

  public void getPropertyValue(Long propertyValueId){
    propertyValueRepository.findOne(propertyValueId);
  }

  public ImageModel saveMultipartImage(MultipartFile image, PropertyValue propertyValue, String tag){
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

  public ImageModel saveByteImage(byte[] image){
    ImageModel imageModel = null;
    if(image!=null){
      imageModel = new ImageModel();
      //imageModel.setOriginalFilename(image.getOriginalFilename());
      imageModel = imageModelRepository.save(imageModel);
      HelperFunctions.saveAllSizeImages(imageModel.getId().toString(), image);
    }
    return imageModel;
  }

  /***
   * As a convention this function saves the images in order in which it appears in the model.
   */
  /* public List<ImageModel> saveImagesInOrder(List<MultipartFile> images, PropertyValue propertyValue){
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
   */
  public String getImageDir(){
    return HelperFunctions.getImageDir(getClass().getResourceAsStream("/application/AppConstants.xml"));
  }

  public void deletePropertyField(long fieldId){
    propertyFieldRepository.deletePropertyFieldById(fieldId);
  }

  public void deletePropertySubGroup(long subgroupId){
    propertySubGroupRepository.deletePropertySubGroupById(subgroupId);
  }

  public void deletePropertyGroup(long groupId){
    propertyGroupRepository.deletePropertyGroupById(groupId);
  }

  public void deleteImageModel(long imageId){
    imageModelRepository.deleteImageModelById(imageId);
  }

  public Map<Long, PropertyField> getPropertyFieldByCategoryIdMappedByPropertyFieldId(Long categoryId){
    List<PropertyField> propertyFields = propertyFieldRepository.findPropertyFieldsByCategoryIdOrderByPropertyFieldId(categoryId);
    Map<Long, PropertyField> propertyFieldMap = new HashMap<Long, PropertyField>();
    for(int i=0; i<propertyFields.size(); i++){
      propertyFieldMap.put(propertyFields.get(i).getId(), propertyFields.get(i));
    }
    return propertyFieldMap;
  }

  public List<PropertyValue> populatePropertyValues(Map<Long, PropertyField> propertyFieldMap, List<Long> fieldIds, List<String> values, Item item){
    List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
    if(fieldIds==null){
      return propertyValues;
    }
    for(int i=0; i<fieldIds.size(); i++){
      PropertyValue propertyValue = new PropertyValue();
      propertyValue.setValue(values.get(i));
      propertyValue.setPropertyField(propertyFieldMap.get(fieldIds.get(i)));
      propertyValue.setItem(item);
      propertyValues.add(propertyValue);
    }
    return propertyValues;
  }

  public Map<Long, ImageModel> getImageModelMetaByCategoryIdMappedByImageModelId(Long categoryId){
    List<ImageModel> imageModels = imageModelRepository.findImageModelsByCategoryIdOrderByImageModelId(categoryId);
    Map<Long, ImageModel> imageModelMap = new HashMap<Long, ImageModel>();
    for(int i=0; i<imageModels.size(); i++){
      imageModelMap.put(imageModels.get(i).getId(), imageModels.get(i));
    }
    return imageModelMap;
  }

  public List<ImageModel> populateImageModels(Map<Long, ImageModel> metaImageModelMap, List<byte[]> byteImages, List<Long> imageMetaIds, Item item){
    if(byteImages==null){
      return null;
    }
    List<ImageModel> valueImageModels = new ArrayList<ImageModel>();
    for(int i=0; i<byteImages.size(); i++){
      if(byteImages.get(i)==null){
        continue;
      }
      ImageModel valueImageModel = saveByteImage(byteImages.get(i));
      valueImageModel.setItem(item);
      valueImageModel.setTag(metaImageModelMap.get(imageMetaIds.get(i)).getTag());
      valueImageModel.setImageModelMetadata(metaImageModelMap.get(imageMetaIds.get(i)));
      valueImageModels.add(valueImageModel);
    }
    return valueImageModels;
  }

  public Map<Long, PropertyValue> getPropertyValuesMappedByPropertyField(List<PropertyValue> propertyValues){
    Map<Long, PropertyValue> valueMap = new HashMap<Long, PropertyValue>();
    for(int i=0; i<propertyValues.size(); i++){
      valueMap.put(propertyValues.get(i).getPropertyField().getId(), propertyValues.get(i));
    }
    return valueMap;
  }

  public Map<Long, ImageModel> getImageModelValuesMappedByImageModelMeta(List<ImageModel> valueImageModels){
    Map<Long, ImageModel> valueImageModelMap = new HashMap<Long, ImageModel>();
    for(int i=0; i<valueImageModels.size(); i++){
      valueImageModelMap.put(valueImageModels.get(i).getImageModelMetadata().getId(), valueImageModels.get(i));
    }
    return valueImageModelMap;
  }

  public Map<Long, PropertyValue> getPropertyValuesMappedByPropertyValue(List<PropertyValue> propertyValues){
    Map<Long, PropertyValue> valueMap = new HashMap<Long, PropertyValue>();
    for(int i=0; i<propertyValues.size(); i++){
      valueMap.put(propertyValues.get(i).getId(), propertyValues.get(i));
    }
    return valueMap;
  }

  public Map<Long, ImageModel> getImageModelValuesMappedByImageModelValue(List<ImageModel> imageModelValues){
    Map<Long, ImageModel> valueMap = new HashMap<Long, ImageModel>();
    for(int i=0; i<imageModelValues.size(); i++){
      valueMap.put(imageModelValues.get(i).getId(), imageModelValues.get(i));
    }
    return valueMap;
  }

  public Item updateImageModelValues(Map<Long, ImageModel> metaImageModels, List<byte[]> byteImages, List<Long> metaImageIds, List<Long> valueIds, Item item){
    if(byteImages==null){
      return item;
    }
    for(int i=0; i<byteImages.size(); i++){
      if(byteImages.get(i) == null){//no image upload
        continue;
      }
      if(valueIds!=null && i<valueIds.size() && valueIds.get(i) != null){//image replacement
        HelperFunctions.saveAllSizeImages(valueIds.get(i).toString(), byteImages.get(i));
      }
      else{//new image
        ImageModel valueImageModel = saveByteImage(byteImages.get(i));
        valueImageModel.setItem(item);
        valueImageModel.setTag(metaImageModels.get(metaImageIds.get(i)).getTag());
        valueImageModel.setImageModelMetadata(metaImageModels.get(metaImageIds.get(i)));
        item.getImageModels().add(valueImageModel);
      }
    }
    return item;
  }

  public List<PropertyValue> updatePropertyValues(Map<Long, PropertyValue> propertyValueMap, List<Long> valueIds, List<String> values){
    for(int i=0; valueIds!=null && i<valueIds.size(); i++){
      propertyValueMap.get(valueIds.get(i)).setValue(values.get(i));
    }
    return new ArrayList<PropertyValue>(propertyValueMap.values());
  }

  //  public List<PropertyValue> getPropertyValueListByOwnerIdAndCategoryId(Long ownerId, Long categoryId){
  //    
  //  }
  
}

