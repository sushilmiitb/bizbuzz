package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.model.PropertyGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertySubGroup;
import com.bizbuzz.model.PropertyValue;

public class ProductDetailDTO {
  List<Long> imagesMetaId;
  List<Long> imagesValueId;
  List<String> imagesHidden;
  List<MultipartFile> images;
  List<Long> fieldIds;
  List<Long> valueIds;
  List<String> values;
  
 /* public void initialize(PropertyMetadata metadata){
    int imageNumber = metadata.getImageModels().size();
    this.images = new ArrayList<MultipartFile>(imageNumber);
    this.imagesHidden = new ArrayList<String>(imageNumber);
    
    this.propertyValue = new PropertyValue();
    this.propertyValue.initialize(metadata.getImageModels().size(), metadata.getPropertyGroups().size());
    for(int i=0; i<metadata.getPropertyGroups().size(); i++){
      PropertyGroup pg = propertyValue.getPropertyGroups().get(i);
      pg.setPropertySubGroups(metadata.getPropertyGroups().get(i).getPropertySubGroups().size());
      for(int j=0; j<metadata.getPropertyGroups().get(i).getPropertySubGroups().size(); j++){
        PropertySubGroup psg = pg.getPropertySubGroups().get(j);
        psg.setPropertyFields(metadata.getPropertyGroups().get(i).getPropertySubGroups().get(j).getPropertyFields().size());
      }
    }
  }*/
  
//  public List<MultipartFile> getImagesInOrder(){
//    List<MultipartFile> imageFiles = new ArrayList<MultipartFile>();
//    imageFiles.add(0, primaryImage);
//    imageFiles.add(1, image1);
//    imageFiles.add(2, image2);
//    imageFiles.add(3, image3);
//    return imageFiles;
//  }
  
  public List<byte[]> getByteImagesFromBase64InOrder(){
    List<byte[]> imageFiles = new ArrayList<byte[]>(imagesHidden.size());
    for(int i=0; i<imagesHidden.size();i++){
      if(imagesHidden.get(i)!=null){
        imageFiles.add(i, Base64.decodeBase64(imagesHidden.get(i).substring(22)));
      }
      else{
        imageFiles.add(i, null);
      }
    }
    
//    if(primaryImageHidden!=null)
//      imageFiles.set(0, Base64.decodeBase64(primaryImageHidden.substring(22)));
//    if(image1Hidden!=null)
//      imageFiles.set(1, Base64.decodeBase64(image1Hidden.substring(22)));
//    if(image2Hidden!=null)
//      imageFiles.set(2, Base64.decodeBase64(image2Hidden.substring(22)));
//    if(image3Hidden!=null)
//      imageFiles.set(3, Base64.decodeBase64(image3Hidden.substring(22)));
    
//    
//    if(primaryImageHidden!=null)
//      imageFiles.set(0, DatatypeConverter.parseBase64Binary(primaryImageHidden));
//    if(image1Hidden!=null)
//      imageFiles.set(1, DatatypeConverter.parseBase64Binary(image1Hidden));
//    if(image2Hidden!=null)
//      imageFiles.set(2, DatatypeConverter.parseBase64Binary(image2Hidden));
//    if(image3Hidden!=null)
//      imageFiles.set(3, DatatypeConverter.parseBase64Binary(image3Hidden));
//    
    return imageFiles;
  }
  
//  public void setImagesInOrder(List<MultipartFile> images){
//    primaryImage = images.get(0);
//    image1 = images.get(1);
//    image2 = images.get(2);
//    image3 = images.get(3);
//  }
  
  
  public List<String> getImagesHidden() {
    return imagesHidden;
  }
  
  public List<Long> getFieldIds() {
    return fieldIds;
  }

  public void setFieldIds(List<Long> fieldIds) {
    this.fieldIds = fieldIds;
  }

  public List<Long> getValueIds() {
    return valueIds;
  }

  public void setValueIds(List<Long> valueIds) {
    this.valueIds = valueIds;
  }

  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public void setImagesHidden(List<String> imageHidden) {
    this.imagesHidden = imageHidden;
  }
  public List<MultipartFile> getImages() {
    return images;
  }
  public void setImages(List<MultipartFile> images) {
    this.images = images;
  }

  public List<Long> getImagesMetaId() {
    return imagesMetaId;
  }

  public void setImagesMetaId(List<Long> imagesMetaId) {
    this.imagesMetaId = imagesMetaId;
  }

  public List<Long> getImagesValueId() {
    return imagesValueId;
  }

  public void setImagesValueId(List<Long> imagesValueId) {
    this.imagesValueId = imagesValueId;
  }
  
}
