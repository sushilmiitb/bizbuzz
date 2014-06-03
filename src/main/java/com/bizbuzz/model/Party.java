package com.bizbuzz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="party")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="party_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Party implements Serializable{
  private static final long serialVersionUID = 4009473233597932062L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  /**
   * BioData of the party
   */
  @OneToMany(mappedBy="party", fetch=FetchType.LAZY)
  private List<PhoneNumber> phoneNumbers;
  
  @OneToMany(mappedBy="party", fetch=FetchType.LAZY)
  private List<Address> addresses;
  
  @OneToMany(mappedBy="fromParty", fetch=FetchType.EAGER)
  private List<Connection> toPartys;
  
  @OneToMany(mappedBy="toParty", fetch=FetchType.EAGER)
  private List<Connection> fromPartys;
  
  /**
   * This function models the addToParty function for many-to-many
   * relationship Connection. Using this function one can add Target
   * Connection Party to the this party
   * @param toParty
   * @param connectionType
   */
  public void addToParty(Party toParty, Connection.ConnectionType connectionType) {
    Connection connection = new Connection();
    connection.setToParty(toParty);
    connection.setFromParty(this);
    connection.setToPartyId(toParty.getId());
    connection.setFromPartyId(this.getId());
    connection.setConnectionType(connectionType);
    this.toPartys.add(connection);    
    toParty.getFromPartys().add(connection);
  }
  
  /**
   * This function models the addFromParty function for many-to-many
   * relationship Connection. Using this function one can add Source
   * Connection party to the this party.
   * @param fromParty
   * @param connectionType
   */
  public void addFromParty(Party fromParty, Connection.ConnectionType connectionType) {
    Connection connection = new Connection();
    connection.setFromParty(fromParty);
    connection.setToParty(this);
    connection.setFromPartyId(fromParty.getId());
    connection.setToPartyId(this.getId());
    connection.setConnectionType(connectionType);
    this.fromPartys.add(connection);    
    fromParty.getToPartys().add(connection);
  }

  
  /**
   * Getters and Setters
   */
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<Connection> getToPartys() {
    return toPartys;
  }

  public void setToPartys(List<Connection> toPartys) {
    this.toPartys = toPartys;
  }

  public List<Connection> getFromPartys() {
    return fromPartys;
  }

  public void setFromPartys(List<Connection> fromPartys) {
    this.fromPartys = fromPartys;
  }
}