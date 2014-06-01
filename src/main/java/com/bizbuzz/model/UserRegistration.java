package com.bizbuzz.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

  @Entity
  @Table(name="userregistration")
  public class UserRegistration implements Serializable {
    private static final long serialVersionUID = -5498033954327208834L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name ="firstname")
    private String firstname;
    
    @Column(name ="lastname")
    private String lastname;
    
    @Column(name = "companyname")
    private String companyname;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "emailid")
    private String emailid;
    
    @Column(name = "phonenumber")
    private Long phonenumber;
    
    @Column(name = "isrememberme")
    private String isrememberme;

  
    public Long getId() {
      return id;
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
      UserRegistration other = (UserRegistration) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      return true;
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
    public String getCompanyname() {
      return companyname;
    }
    public void setCompanyname(String companyname) {
      this.companyname = companyname;
    }
    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
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
    public String getIsrememberme() {
      return isrememberme;
    }
    public void setIsrememberme(String isrememberme) {
      this.isrememberme = isrememberme;
    }
    public String getDesignation() {
      return designation;
    }
    public void setDesignation(String designation) {
      this.designation = designation;
    }
    public void setId(Long id) {
      this.id = id;
    }
}
