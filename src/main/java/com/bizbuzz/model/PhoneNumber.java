package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author Sushil
 *
 */
@Entity
public class PhoneNumber implements Serializable {
  private static final long serialVersionUID = 5138928529207030760L;
  public static enum PhoneType{MOBILE, LANDLINE, FAX};
  
  @Id
  @GeneratedValue
  private Long id;
  private PhoneType phoneType;
  private String countryCode;
  private String areaCode;
  private String contactNumber;
  
  @ManyToOne
  @JoinColumn(name="party_id")
  private Party party;
  
  /**
   * 
   * getters and setters
   */
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public PhoneType getPhoneType() {
    return phoneType;
  }
  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }
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
}
