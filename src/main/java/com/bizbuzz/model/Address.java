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
  
  @Id
  @GeneratedValue
  private Long id;
  private String toName;
  private String address1;
  private String address2;
  private String landmark;
  private String city;
  private String postalCode;
  private String stateGeoId;
  
  @ManyToOne
  @JoinColumn(name="party_id")
  private Party party;

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

  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }

  public Long getId() {
    return id;
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
