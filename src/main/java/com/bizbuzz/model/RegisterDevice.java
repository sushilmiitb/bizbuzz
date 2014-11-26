package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RegisterDevice implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  private String deviceRegistrationId;
  
  @OneToOne
  @JoinColumn(name="party_id")
  private Party party;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getDeviceRegistrationId() {
    return deviceRegistrationId;
  }

  public void setDeviceRegistrationId(String deviceRegistrationId) {
    this.deviceRegistrationId = deviceRegistrationId;
  }

  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }
  
}
