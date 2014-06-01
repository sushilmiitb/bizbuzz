package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author sushil
 *
 */

@Entity
public class TruckModel implements Serializable{
  @Id
  @GeneratedValue
  private Long id;
  
  private String makeCompany;
  private String modelNumber;
  private String genericType; //what is particular type of truck commonly called as
  private String numberOfWheels;
  private Long lengthFeet;
  private Long heightFeet;
  private Long widthFeet;
  private Long gwt; //gross weight tonnage
  private Long permittedTonnage; //weight that is permitted for truck to carry
  private Long chassisType;
  private Long dieselCapacity;
  
  @OneToMany(mappedBy="truckModel")
  private List<Truck> trucks = new ArrayList<Truck>();

  public String getMakeCompany() {
    return makeCompany;
  }

  public void setMakeCompany(String makeCompany) {
    this.makeCompany = makeCompany;
  }

  public String getModelNumber() {
    return modelNumber;
  }

  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public String getGenericType() {
    return genericType;
  }

  public void setGenericType(String genericType) {
    this.genericType = genericType;
  }

  public String getNumberOfWheels() {
    return numberOfWheels;
  }

  public void setNumberOfWheels(String numberOfWheels) {
    this.numberOfWheels = numberOfWheels;
  }

  public Long getLengthFeet() {
    return lengthFeet;
  }

  public void setLengthFeet(Long lengthFeet) {
    this.lengthFeet = lengthFeet;
  }

  public Long getHeightFeet() {
    return heightFeet;
  }

  public void setHeightFeet(Long heightFeet) {
    this.heightFeet = heightFeet;
  }

  public Long getWidthFeet() {
    return widthFeet;
  }

  public void setWidthFeet(Long widthFeet) {
    this.widthFeet = widthFeet;
  }

  public Long getGwt() {
    return gwt;
  }

  public void setGwt(Long gwt) {
    this.gwt = gwt;
  }

  public Long getPermittedTonnage() {
    return permittedTonnage;
  }

  public void setPermittedTonnage(Long permittedTonnage) {
    this.permittedTonnage = permittedTonnage;
  }

  public Long getChassisType() {
    return chassisType;
  }

  public void setChassisType(Long chassisType) {
    this.chassisType = chassisType;
  }

  public Long getDieselCapacity() {
    return dieselCapacity;
  }

  public void setDieselCapacity(Long dieselCapacity) {
    this.dieselCapacity = dieselCapacity;
  }

  public List<Truck> getTrucks() {
    return trucks;
  }

  public void setTrucks(List<Truck> trucks) {
    this.trucks = trucks;
  }
}
