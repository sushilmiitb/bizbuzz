package com.bizbuzz.dto;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.UserLogin;

public class PersonRegistrationDTO {
  private Company company;
  private Person person;
  private UserLogin userLogin;
  private PhoneNumber phoneNumber;
  
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
  
  
}
