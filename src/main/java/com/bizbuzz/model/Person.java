package com.bizbuzz.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("person")
public class Person extends Party{
  private static final long serialVersionUID = 1L;

  //public static enum PersonRole {SOLO_OWNER, CO_OWNER, PROMOTER, EMPLOYEE};
  
  private String firstName;
  private String lastName;
  private String middleName;
  private char gender;
  private String personRole;
  @OneToOne
  @JoinColumn(name="user_id")
  private UserLogin userId;
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public String getPersonRole() {
    return personRole;
  }
  public void setPersonRole(String personRole) {
    this.personRole = personRole;
  }
  public UserLogin getUserId() {
    return userId;
  }
  public void setUserId(UserLogin userId) {
    this.userId = userId;
  }
  
}
