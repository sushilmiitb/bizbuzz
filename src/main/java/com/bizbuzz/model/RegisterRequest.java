package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author sushil
 *
 */
@Entity
public class RegisterRequest implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="from_party_id", referencedColumnName="id")
  private Party fromParty;
  private String toPartyPhoneNumber;
  
  @OneToOne
  @JoinColumn(name="private_group_id", referencedColumnName="id")
  private PrivateGroup privateGroup;
  
  public Party getFromParty() {
    return fromParty;
  }
  public void setFromParty(Party fromParty) {
    this.fromParty = fromParty;
  }
  public String getToPartyPhoneNumber() {
    return toPartyPhoneNumber;
  }
  public void setToPartyPhoneNumber(String toPartyPhoneNumber) {
    this.toPartyPhoneNumber = toPartyPhoneNumber;
  }
  public PrivateGroup getPrivateGroup() {
    return privateGroup;
  }
  public void setPrivateGroup(PrivateGroup privateGroup) {
    this.privateGroup = privateGroup;
  }
  
}
