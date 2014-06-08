package com.bizbuzz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@IdClass(ConnectionId.class)
public class Connection{
  
  public static enum ConnectionType {SELLER_BUYER, COMPANY_PERSON, GROUP_MEMBERS, GROUPOWNER_GROUP, SELLER_AGENT, AGENT_BUYER};
  @Id
  private Long fromPartyId;
  @Id
  private Long toPartyId;
  
  @ManyToOne
  @PrimaryKeyJoinColumn(name="from_party_id", referencedColumnName="id")
  private Party fromParty;
  
  @ManyToOne
  @PrimaryKeyJoinColumn(name="to_party_id", referencedColumnName="id")
  private Party toParty;
  
  private ConnectionType connectionType;


  public Connection(){
    
  }
  
  public Connection(Party fromParty, Party toParty, ConnectionType connectionType){
    this.setFromParty(fromParty);
    this.setToParty(toParty);
    this.fromPartyId = fromParty.getId();
    this.toPartyId = toParty.getId();
    this.connectionType = connectionType;
  }
  
  public Connection(Party fromParty, Party toParty){
    this.setFromParty(fromParty);
    this.setToParty(toParty);
    this.fromPartyId = fromParty.getId();
    this.toPartyId = toParty.getId();
  }
  
  /**
   * getters and setters
   */
  
  public Long getFromPartyId() {
    return fromPartyId;
  }

  public void setFromPartyId(Long fromPartyId) {
    this.fromPartyId = fromPartyId;
  }

  public Long getToPartyId() {
    return toPartyId;
  }

  public void setToPartyId(Long toPartyId) {
    this.toPartyId = toPartyId;
  }

  public Party getFromParty() {
    return fromParty;
  }

  public void setFromParty(Party fromParty) {
    this.fromParty = fromParty;
  }

  public Party getToParty() {
    return toParty;
  }

  public void setToParty(Party toParty) {
    this.toParty = toParty;
  }

  public ConnectionType getConnectionType() {
    return connectionType;
  }

  public void setConnectionType(ConnectionType connectionType) {
    this.connectionType = connectionType;
  }

}
