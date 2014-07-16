package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="property_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Property implements Serializable{
  private static final long serialVersionUID = 4009473233597932062L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;
  
  private Boolean isImagePresent;
  private String primaryImage;
  private String image1;
  private String image2;
  private String image3;
  
  private String group1;
  private String group1Subgroup1;
  private String group1Subgroup1Property1;
  private String group1Subgroup1Property2;
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
  
}
