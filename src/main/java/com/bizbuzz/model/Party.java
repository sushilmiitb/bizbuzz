package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 
 * @author mukesh
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Party implements Serializable {
  private static final long serialVersionUID = 4009473233597932062L;
  
  public static enum PartyType {PARTY_GROUP, PERSON};
  
  @Id
  @GeneratedValue
  private Long id;
  //private PartyType type;
  @OneToMany(mappedBy="party")
  private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
  @OneToMany(mappedBy="party")
  private List<Address> addresses = new ArrayList<Address>();
  
  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date updatedAt;
  
  public Long getId() {
    return id;
  }
  public Date getCreatedAt() {
    return createdAt;
  }
  public Date getUpdatedAt() {
    return updatedAt;
  }
  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }
  public List<Address> getAddresses() {
    return addresses;
  }
  public void addPhoneNumber(PhoneNumber phoneNumber) {
    this.phoneNumbers.add(phoneNumber);
  }
  public void addAddress(Address address) {
    this.addresses.add(address);
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
    Party other = (Party) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
