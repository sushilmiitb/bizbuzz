package com.bizbuzz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contactus")
public class ContactUs {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "role")
  private String role;

  @Column(name = "companyname")
  private String companyname;

  @Column(name = "emailid")
  private String emailid;

  @Column(name = "phonenumber")
  private Long phonenumber;

  @Column(name = "message")
  private String message;

  public Long getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  public String getEmailid() {
    return emailid;
  }

  public void setEmailid(String emailid) {
    this.emailid = emailid;
  }

  public Long getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(Long phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ContactUs contactUs = (ContactUs) obj;
    if (id == null) {
      if (contactUs.id != null)
        return false;
    } else if (!id.equals(contactUs.id))
      return false;
    return true;
  }

}
