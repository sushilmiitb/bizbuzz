package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.dto.ProductDetailDTO;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;

public interface PropertyService {
  public PropertyMetadata getPropertyMetadata(Person seller, Integer depth, Long categoryId);
  public PropertyMetadata getPropertyMetadata(Long categoryId);
  public PropertyMetadata savePropertyMetadata(PropertyMetadata propertyMetadata, Long categoryId);
  public PropertyMetadata saveExistingPropertyMetadata(Long propertyMetadataId, Long categoryId);
  public PropertyValue savePropertyValue(PropertyValue propertyValue);
  public void getPropertyValue(Long propertyValueId);
  public ImageModel saveImage(MultipartFile image, PropertyValue propertyValue, String tag);
  public List<ImageModel> saveImagesInOrder(List<MultipartFile> images, PropertyValue propertyValue);
  public List<ImageModel> saveByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue);
  public ImageModel saveByteImage(byte[] image, String tag);
  public List<ImageModel> updateByteImagesInOrder(List<byte[]> images, PropertyValue propertyValue);
  public ImageModel updateByteImage(byte[] image, ImageModel imageModel, String tag);
  public PropertyValue updatePropertyValue(PropertyValue newPropertyValue, PropertyValue oldPropertyValue);
  public String getImageDir();
}
