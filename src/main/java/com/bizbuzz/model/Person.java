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
public class Person extends Party {
  private static final long serialVersionUID = 7331861590248669955L;
  
  public static enum Role {
    DRIVER, EMPLOYEE, OWNER, ADMIN
  };
  private String firstName;
  private String lastName;
  private char gender;
  private String idPath;
  public String getIdPath() {
    return idPath;
  }
  public void setIdPath(String idPath) {
    this.idPath = idPath;
  }
  //private Role role;
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
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
}
