package com.bizbuzz.dto;

import java.util.Map;

import com.bizbuzz.model.Person;

public class SellerAddConnectionResponseAjaxDTO {
  private String firstName;
  private String middleName;
  private String lastName;
  private Long id;
  private String phoneNumber;
  private Map<String, String> errors;
  
  public SellerAddConnectionResponseAjaxDTO(){
    
  }
  
  public SellerAddConnectionResponseAjaxDTO(Person person){
    firstName = person.getFirstName();
    middleName = person.getLastName();
    lastName = person.getLastName();
    id= person.getId();
    phoneNumber = person.getUserId().getId();
  }
  public void addDetails(Person person){
    firstName = person.getFirstName();
    middleName = person.getLastName();
    lastName = person.getLastName();
    id= person.getId();
    phoneNumber = person.getUserId().getId();
  }
  
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public Map<String, String> getErrors() {
    return errors;
  }
  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }
  
}
