package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.*;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.bizbuzz.model.SecurityGroup;

/**
 * 
 * @author mukesh
 *
 */
@Entity
@Table(name="user_login")
public class UserLogin implements Serializable {
  private static final long serialVersionUID = -5498033954327208834L;

  @Id
  @Size(min=5, max=20)
  private String id;//can be phone number or email id
  private String passwordHash;
  private boolean enabled;
  @ManyToMany(fetch=FetchType.EAGER)
  @JoinTable(
      name="user_login_security_group",
      joinColumns=@JoinColumn(name="user_login_id"),
      inverseJoinColumns=@JoinColumn(name="security_group_id")
  )
  private Set<SecurityGroup> securityGroups = new HashSet<SecurityGroup>();
  
  @OneToOne(mappedBy="userId")
  private Person person;
  
  //private Party party;
  
  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date updatedAt;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPasswordHash() {
    return passwordHash;
  }
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
  public boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  public Set<SecurityGroup> getSecurityGroups() {
    return securityGroups;
  }
  public void setSecurityGroups(Set<SecurityGroup> securityGroups) {
    this.securityGroups = securityGroups;
  }
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
  public void addSecurityGroup(SecurityGroup securityGroup) {
    this.securityGroups.add(securityGroup);
  }
  
  public Person getPerson() {
    return person;
  }
  public void setPerson(Person person) {
    this.person = person;
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
    UserLogin other = (UserLogin) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}