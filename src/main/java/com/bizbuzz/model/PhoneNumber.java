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
public class PhoneNumber implements Serializable {
  private static final long serialVersionUID = 5138928529207030760L;
  
  @Id
  @GeneratedValue
  private Long id;
  private String countryCode;
  private String areaCode;
  private String contactNumber;
  @ManyToOne
  @JoinColumn(name="party_id")
  private Party party;
  public String getCountryCode() {
    return countryCode;
  }
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  public String getAreaCode() {
    return areaCode;
  }
  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }
  public String getContactNumber() {
    return contactNumber;
  }
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
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
}
