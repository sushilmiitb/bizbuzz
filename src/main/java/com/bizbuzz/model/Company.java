package com.bizbuzz.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("company")
public class Company extends Party{
  private static final long serialVersionUID = 1L;

  public static enum CompanyRegistrationType {SOLE_PROPRIETORSHIP, PARTNERSHIP, LLP, PVT_LTD, PUBLIC};
  
  
  private String companyName;
  private String companyRole;
  private CompanyRegistrationType companyRegistrationType;
  private String registrationId;
  
  /**
   * 
   * getters and setters
   */
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public CompanyRegistrationType getCompanyRegistrationType() {
    return companyRegistrationType;
  }
  public void setCompanyRegistrationType(
      CompanyRegistrationType companyRegistrationType) {
    this.companyRegistrationType = companyRegistrationType;
  }
  public String getRegistrationId() {
    return registrationId;
  }
  public void setRegistrationId(String registrationId) {
    this.registrationId = registrationId;
  }
  public String getCompanyRole() {
    return companyRole;
  }
  public void setCompanyRole(String companyRole) {
    this.companyRole = companyRole;
  }
}
