package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author mukesh
 *
 */
@Embeddable
public class OrderAddress implements Serializable {
  private static final long serialVersionUID = -1062245271995012225L;
  
  private String toName;
  private String address1;
  private String address2;
  private String landmark;
  private String city;
  private String postalCode;
  private String stateGeoId;
  private String phoneNumber;
  public String getToName() {
    return toName;
  }
  public void setToName(String toName) {
    this.toName = toName;
  }
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  public String getLandmark() {
    return landmark;
  }
  public void setLandmark(String landmark) {
    this.landmark = landmark;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  public String getStateGeoId() {
    return stateGeoId;
  }
  public void setStateGeoId(String stateGeoId) {
    this.stateGeoId = stateGeoId;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
}
