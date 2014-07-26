package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * 
 * @author sushil
 * Any Changes in this file require changes in dto.ProductDetailDTO, seller/viewproductupload.jsp, ItemService/saveFiles,
 * PropertyValue (for file changes), 
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="property_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Property implements Serializable{
  private static final long serialVersionUID = 4009473233597932062L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  
  static public final String tagsArray[] = {"primaryImageModel", "image1Model", "image2Model", "image3Model"};
  
  @OneToOne
  @JoinColumn(name="primary_image_model_id", referencedColumnName="id")
  private ImageModel primaryImageModel;
  @OneToOne
  @JoinColumn(name="image1_model_id", referencedColumnName="id")
  private ImageModel image1Model;
  @OneToOne
  @JoinColumn(name="image2_model_id", referencedColumnName="id")
  private ImageModel image2Model;
  @OneToOne
  @JoinColumn(name="image3_model_id", referencedColumnName="id")
  private ImageModel image3Model;
  
  private Boolean isImagePresent;
  private String primaryImage;
  private String image1;
  private String image2;
  private String image3;
  
  private String group1;
  private String group1Subgroup1;
  private String group1Subgroup1Property1;
  private String group1Subgroup1Property2;
  
  public List<String> getImageTags(){
    List<String> tags = new ArrayList<String>();
    for(int i=0; i<tagsArray.length;i++){
      tags.add(i, tagsArray[i]);
    }
    return tags;
  }
  
  public void setImageModelsInOrder(List<ImageModel> imageModels){
    setPrimaryImageModel(imageModels.get(0));
    setImage1Model(imageModels.get(1));
    setImage2Model(imageModels.get(2));
    setImage3Model(imageModels.get(3));
  }
  
  public List<ImageModel> getImageModelsInOrder(){
    List<ImageModel> imageModels = new ArrayList<ImageModel>();
    imageModels.add(0, getPrimaryImageModel());
    imageModels.add(1, getImage1Model());
    imageModels.add(2, getImage2Model());
    imageModels.add(3, getImage3Model());
    return imageModels;
  }
  
  public Boolean getIsImagePresent() {
    return isImagePresent;
  }
  public void setIsImagePresent(Boolean isImagePresent) {
    this.isImagePresent = isImagePresent;
  }
  public String getPrimaryImage() {
    return primaryImage;
  }
  public void setPrimaryImage(String primaryImage) {
    this.primaryImage = primaryImage;
  }
  public String getImage1() {
    return image1;
  }
  public void setImage1(String image1) {
    this.image1 = image1;
  }
  public String getImage2() {
    return image2;
  }
  public void setImage2(String image2) {
    this.image2 = image2;
  }
  public String getImage3() {
    return image3;
  }
  public void setImage3(String image3) {
    this.image3 = image3;
  }
  public String getGroup1() {
    return group1;
  }
  public void setGroup1(String group1) {
    this.group1 = group1;
  }
  public String getGroup1Subgroup1() {
    return group1Subgroup1;
  }
  public void setGroup1Subgroup1(String group1Subgroup1) {
    this.group1Subgroup1 = group1Subgroup1;
  }
  public String getGroup1Subgroup1Property1() {
    return group1Subgroup1Property1;
  }
  public void setGroup1Subgroup1Property1(String group1Subgroup1Property1) {
    this.group1Subgroup1Property1 = group1Subgroup1Property1;
  }
  public String getGroup1Subgroup1Property2() {
    return group1Subgroup1Property2;
  }
  public void setGroup1Subgroup1Property2(String group1Subgroup1Property2) {
    this.group1Subgroup1Property2 = group1Subgroup1Property2;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }
  public ImageModel getPrimaryImageModel() {
    return primaryImageModel;
  }
  public void setPrimaryImageModel(ImageModel primaryImageModel) {
    this.primaryImageModel = primaryImageModel;
  }
  public ImageModel getImage1Model() {
    return image1Model;
  }
  public void setImage1Model(ImageModel image1Model) {
    this.image1Model = image1Model;
  }
  public ImageModel getImage2Model() {
    return image2Model;
  }
  public void setImage2Model(ImageModel image2Model) {
    this.image2Model = image2Model;
  }
  public ImageModel getImage3Model() {
    return image3Model;
  }
  public void setImage3Model(ImageModel image3Model) {
    this.image3Model = image3Model;
  }
    
}
