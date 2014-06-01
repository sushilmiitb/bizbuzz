package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.bizbuzz.model.SecurityGroup;

/**
 * 
 * @author mukesh
 *
 */
@Entity
public class UserLogin implements Serializable {
  private static final long serialVersionUID = -5498033954327208834L;

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String passwordHash;
  private boolean enabled;
  @ManyToMany(fetch=FetchType.EAGER)
  @JoinTable(
      name="user_login_security_group",
      joinColumns=@JoinColumn(name="user_login_id"),
      inverseJoinColumns=@JoinColumn(name="security_group_id")
  )
  private Set<SecurityGroup> securityGroups = new HashSet<SecurityGroup>();
  
  //private Party party;
  
  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date updatedAt;

  public Long getId() {
    return id;
  }
  public String getEmail() {
    return username;
  }
  public void setEmail(String email) {
    this.username = email;
  }
  public String getPasswordHash() {
    return passwordHash;
  }
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
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
  public void addSecurityGroup(SecurityGroup securityGroup) {
    this.securityGroups.add(securityGroup);
  }
  public Date getCreatedAt() {
    return createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }
}