package com.bizbuzz.dto;

import java.util.Map;

import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;

public class SellerAddConnectionResponseAjaxDTO {
  private String firstName;
  private String middleName;
  private String lastName;
  private Long id;
  private String phoneNumber;
  private Map<String, String> errors;
  private String groupName;
  private Long groupId;

  public SellerAddConnectionResponseAjaxDTO(){

  }

  public SellerAddConnectionResponseAjaxDTO(Person person){
    firstName = person.getFirstName();
    middleName = person.getLastName();
    lastName = person.getLastName();
    id= person.getId();
    phoneNumber = person.getUserId().getId();
  }
  public void addDetails(Person person, PrivateGroup pg){
    firstName = "";
    middleName = "";
    lastName = "";
    if(person.getFirstName() != null)
      firstName = person.getFirstName();
    if(person.getMiddleName() != null)
      middleName = person.getLastName();
    if(person.getLastName() != null)
      lastName = person.getLastName();
    id= person.getId();
    phoneNumber = person.getUserId().getId();
    if(pg!=null){
      setGroupName(pg.getPrivateGroupName());
      setGroupId(pg.getId());
    }
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

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

}
