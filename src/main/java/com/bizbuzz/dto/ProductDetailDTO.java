package com.bizbuzz.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.utils.MyBase64;

public class ProductDetailDTO {
  private PropertyValue propertyValue;
  MultipartFile primaryImage;
  MultipartFile image1;
  MultipartFile image2;
  MultipartFile image3;
  String primaryImageHidden;
  String image1Hidden;
  String image2Hidden;
  String image3Hidden;
  
  public List<MultipartFile> getImagesInOrder(){
    List<MultipartFile> imageFiles = new ArrayList<MultipartFile>();
    imageFiles.add(0, primaryImage);
    imageFiles.add(1, image1);
    imageFiles.add(2, image2);
    imageFiles.add(3, image3);
    return imageFiles;
  }
  
  public List<byte[]> getByteImagesFromBase64InOrder(){
    List<byte[]> imageFiles = new ArrayList<byte[]>(4);
    for(int i=0; i<4;i++){
      imageFiles.add(i, null);
    }
    
    if(primaryImageHidden!=null)
      imageFiles.set(0, Base64.decodeBase64(primaryImageHidden.substring(22)));
    if(image1Hidden!=null)
      imageFiles.set(1, Base64.decodeBase64(image1Hidden.substring(22)));
    if(image2Hidden!=null)
      imageFiles.set(2, Base64.decodeBase64(image2Hidden.substring(22)));
    if(image3Hidden!=null)
      imageFiles.set(3, Base64.decodeBase64(image3Hidden.substring(22)));
    
    /*
    if(primaryImageHidden!=null)
      imageFiles.set(0, DatatypeConverter.parseBase64Binary(primaryImageHidden));
    if(image1Hidden!=null)
      imageFiles.set(1, DatatypeConverter.parseBase64Binary(image1Hidden));
    if(image2Hidden!=null)
      imageFiles.set(2, DatatypeConverter.parseBase64Binary(image2Hidden));
    if(image3Hidden!=null)
      imageFiles.set(3, DatatypeConverter.parseBase64Binary(image3Hidden));
    */
    return imageFiles;
  }
  
  public void setImagesInOrder(List<MultipartFile> images){
    primaryImage = images.get(0);
    image1 = images.get(1);
    image2 = images.get(2);
    image3 = images.get(3);
  }
  
  public PropertyValue getPropertyValue() {
    return propertyValue;
  }
  public void setPropertyValue(PropertyValue propertyValue) {
    this.propertyValue = propertyValue;
  }
  public MultipartFile getPrimaryImage() {
    return primaryImage;
  }
  public void setPrimaryImage(MultipartFile primaryImage) {
    this.primaryImage = primaryImage;
  }
  public MultipartFile getImage1() {
    return image1;
  }
  public void setImage1(MultipartFile image1) {
    this.image1 = image1;
  }
  public MultipartFile getImage2() {
    return image2;
  }
  public void setImage2(MultipartFile image2) {
    this.image2 = image2;
  }
  public MultipartFile getImage3() {
    return image3;
  }
  public void setImage3(MultipartFile image3) {
    this.image3 = image3;
  }

  public String getPrimaryImageHidden() {
    return primaryImageHidden;
  }

  public void setPrimaryImageHidden(String primaryImageHidden) {
    this.primaryImageHidden = primaryImageHidden;
  }

  public String getImage1Hidden() {
    return image1Hidden;
  }

  public void setImage1Hidden(String image1Hidden) {
    this.image1Hidden = image1Hidden;
  }

  public String getImage2Hidden() {
    return image2Hidden;
  }

  public void setImage2Hidden(String image2Hidden) {
    this.image2Hidden = image2Hidden;
  }

  public String getImage3Hidden() {
    return image3Hidden;
  }

  public void setImage3Hidden(String image3Hidden) {
    this.image3Hidden = image3Hidden;
  }
  
}
