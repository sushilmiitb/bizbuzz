package com.bizbuzz.dto;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.UserLogin;

public class RegistrationPersonRegistrationFormDTO {

  private Company company;
  private Person person;
  private UserLogin userLogin;
  private PhoneNumber phoneNumber;
  private CountryCodeDTO countryCodeDTO;
  private String industry;
  
  public Company getCompany() {
    return company;
  }
  public void setCompany(Company company) {
    this.company = company;
  }
  public Person getPerson() {
    return person;
  }
  public void setPerson(Person person) {
    this.person = person;
  }
  public UserLogin getUserLogin() {
    return userLogin;
  }
  public void setUserLogin(UserLogin userLogin) {
    this.userLogin = userLogin;
  }
  public PhoneNumber getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public CountryCodeDTO getCountryCodeDTO() {
    return countryCodeDTO;
  }
  public void setCountryCodeDTO(CountryCodeDTO countryCodeDTO) {
    this.countryCodeDTO = countryCodeDTO;
  }
  public String getIndustry() {
    return industry;
  }
  public void setIndustry(String industry) {
    this.industry = industry;
  }
  
}
