package com.bizbuzz.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * 
 * @author mukesh
 *
 */
@Entity
@PrimaryKeyJoinColumn
public class PartyGroup extends Party {
  private static final long serialVersionUID = 8027700772262300906L;
  
  public static enum PartyGroupType {
    SHIPPER_COMPANY, TRUCK_COMPANY, TRUCK_AGENT
  };
  
  private String name;
  private String logoImageUrl;
  private Boolean isRegistered;
  public Boolean getIsRegistered() {
    return isRegistered;
  }
  public void setIsRegistered(Boolean isRegistered) {
    this.isRegistered = isRegistered;
  }
  //private PartyGroupType type;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLogoImageUrl() {
    return logoImageUrl;
  }
  public void setLogoImageUrl(String logoImageUrl) {
    this.logoImageUrl = logoImageUrl;
  }

}
