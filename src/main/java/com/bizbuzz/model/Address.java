package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author mukesh
 *
 */
@Entity
public class Address implements Serializable {
  private static final long serialVersionUID = -1321487893947285680L;
  public static enum AddressType{OFFICE, BILLING, FACTORY};
  
  @Id
  @GeneratedValue
  private Long id;
  private String attendantName;
  private String addressLine1;
  private String addressLine2;
  private String landmark;
  private String city;
  private String postalCode;
  private String stateGeoId;
  private AddressType addressType;
  
  @ManyToOne
  @JoinColumn(name="party_id")
  private Party party;

  /**
   * 
   * getters and setters
   */
  
  public Long getId(){
    return id;
  }
  
  public String getName() {
    return attendantName;
  }

  public void setName(String name) {
    this.attendantName = name;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
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

  public AddressType getAddressType() {
    return addressType;
  }

  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Address other = (Address) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
}
