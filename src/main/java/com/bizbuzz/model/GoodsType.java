package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author sushil
 *
 */

@Entity
public class GoodsType implements Serializable{
  @Id
  @GeneratedValue
  private Long id;
  private String goodsClass;
  private String specificName;
  private Boolean isBrittle;
  private String measurementUnit;
  private String valuePerUnit;
  private String description;
  public String getGenericType() {
    return goodsClass;
  }
  public void setGenericType(String genericType) {
    this.goodsClass = genericType;
  }
  public String getSpecificName() {
    return specificName;
  }
  public void setSpecificName(String specificName) {
    this.specificName = specificName;
  }
  public Boolean getIsBrittle() {
    return isBrittle;
  }
  public void setIsBrittle(Boolean isBrittle) {
    this.isBrittle = isBrittle;
  }
  public String getMeasurementUnit() {
    return measurementUnit;
  }
  public void setMeasurementUnit(String measurementUnit) {
    this.measurementUnit = measurementUnit;
  }
  public String getValuePerUnit() {
    return valuePerUnit;
  }
  public void setValuePerUnit(String valuePerUnit) {
    this.valuePerUnit = valuePerUnit;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getGoodsClass() {
    return goodsClass;
  }
  public void setGoodsClass(String goodsClass) {
    this.goodsClass = goodsClass;
  }

}
