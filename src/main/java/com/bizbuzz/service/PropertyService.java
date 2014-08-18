package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.dto.ProductDetailDTO;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyField;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;

public interface PropertyService {
  public PropertyMetadata getPropertyMetadata(Person seller, Integer depth, Long categoryId);
  public PropertyMetadata getPropertyMetadata(Long categoryId);
  public PropertyMetadata savePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId);
  public PropertyMetadata updatePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId);
  //public PropertyMetadata saveExistingPropertyMetadata(Long propertyMetadataId, Long categoryId);
  public PropertyValue savePropertyValue(PropertyValue propertyValue);
  public void getPropertyValue(Long propertyValueId);
  public ImageModel saveMultipartImage(MultipartFile image, PropertyValue propertyValue, String tag);
  //public List<ImageModel> saveImagesInOrder(List<MultipartFile> images, PropertyValue propertyValue);
  //public List<ImageModel> saveByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue);
  public ImageModel saveByteImage(byte[] image);
  //public List<ImageModel> updateByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue);
  //public ImageModel updateByteImage(byte[] image, ImageModel imageModel, String tag);
  //public PropertyValue updatePropertyValue(PropertyValue newPropertyValue, PropertyValue oldPropertyValue);
  public String getImageDir();
  public void deletePropertyField(long fieldId);
  public void deletePropertySubGroup(long subgroupId);
  public void deletePropertyGroup(long groupId);
  public void deleteImageModel(long imageId);
  public Map<Long, PropertyField> getPropertyFieldByCategoryIdMappedByPropertyFieldId(Long categoryId);
  public List<PropertyValue> populatePropertyValues(Map<Long, PropertyField> propertyFieldMap, List<Long> fieldIds, List<String> values, Item item);
  public Map<Long, PropertyValue> getPropertyValuesMappedByPropertyField(List<PropertyValue> propertyValues);
  public Map<Long, ImageModel> getImageModelValuesMappedByImageModelMeta(List<ImageModel> valueImageModels);
  public Map<Long, PropertyValue> getPropertyValuesMappedByPropertyValue(List<PropertyValue> propertyValues);
  public Map<Long, ImageModel> getImageModelValuesMappedByImageModelValue(List<ImageModel> imageModelValues);
  public Item updateImageModelValues(Map<Long, ImageModel> metaImageModels, List<byte[]> byteImages, List<Long> metaImageIds, List<Long> valueIds, Item item);
  public List<PropertyValue> updatePropertyValues(Map<Long, PropertyValue> propertyValueMap, List<Long> valueIds, List<String> values);
  public Map<Long, ImageModel> getImageModelMetaByCategoryIdMappedByImageModelId(Long categoryId);
  public List<ImageModel> populateImageModels(Map<Long, ImageModel> metaImageModelMap, List<byte[]> byteImages, List<Long> imageMetaIds, Item item);
}
