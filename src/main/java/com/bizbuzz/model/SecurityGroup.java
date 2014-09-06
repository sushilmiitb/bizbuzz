package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import org.springframework.data.convert.ReadingConverter;

/**
 * 
 * @author mukesh
 *
 */
@Entity
public class SecurityGroup implements Serializable {
  private static final long serialVersionUID = 5910094699318142229L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String name;
  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(name="security_group_authority", joinColumns=@JoinColumn(name="security_group_id"))
  @Column(name="authority")
  private Set<String> authorities = new HashSet<String>();
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Long getId() {
    return id;
  }
  public Set<String> getAuthorities() {
    return authorities;
  }
  public void addAuthority(String authority) {
    this.authorities.add(authority);
  }
  public void addAuthorities(Collection<String> authorities) {
    this.authorities.addAll(authorities);
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
    SecurityGroup other = (SecurityGroup) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
