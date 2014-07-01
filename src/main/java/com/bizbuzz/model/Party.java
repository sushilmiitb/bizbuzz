package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hsqldb.lib.ArrayListIdentity;

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
  
  private String email;
  
  @OneToMany(mappedBy="party", fetch=FetchType.LAZY)
  private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
  
  @OneToMany(mappedBy="party", fetch=FetchType.LAZY)
  private List<Address> addresses = new ArrayList<Address>();
  
  @OneToMany(mappedBy="fromParty", fetch=FetchType.EAGER)
  private List<Connection> toParties = new ArrayList<Connection>();
  
  @OneToMany(mappedBy="toParty", fetch=FetchType.EAGER)
  private List<Connection> fromParties = new ArrayList<Connection>();
  
  @ManyToOne
  @JoinColumn(name="category_root", referencedColumnName="id")
  private CategoryTree categoryRoot;
  
  @ManyToMany
  @JoinTable(
      name="share",
      joinColumns={@JoinColumn(name="party_id", referencedColumnName="id")},
          inverseJoinColumns={@JoinColumn(name="item_id", referencedColumnName="id")})
  private List<Item> items = new ArrayList<Item>();
  
  @ManyToMany(mappedBy="members")
  private List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();
  
  @OneToMany(mappedBy="sender")
  private List <Chat> sentChat = new ArrayList<Chat>();
  
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
    this.toParties.add(connection);
    toParty.getFromParties().add(connection);
  }
  
  public void addToParty(Connection con){
    this.toParties.add(con);
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
    this.fromParties.add(connection);
    fromParty.getToParties().add(connection);
  }
  
  public void addFromParty(Connection con){
    this.fromParties.add(con);
  }

  public void addPhoneNumber(PhoneNumber phoneNumber){
    this.phoneNumbers.add(phoneNumber);
  }
  
  /**
   * Getters and Setters
   */
  
  public Long getId() {
    return id;
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

  public List<Connection> getToParties() {
    return toParties;
  }

  public void setToParties(List<Connection> toParties) {
    this.toParties = toParties;
  }

  public List<Connection> getFromParties() {
    return fromParties;
  }

  public void setFromParties(List<Connection> fromParties) {
    this.fromParties = fromParties;
  }

  public CategoryTree getCategoryRoot() {
    return categoryRoot;
  }

  public void setCategoryRoot(CategoryTree categoryRoot) {
    this.categoryRoot = categoryRoot;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<Chat> getSentChat() {
    return sentChat;
  }

  public void setSentChat(List<Chat> sentChat) {
    this.sentChat = sentChat;
  }

  public List<ChatRoom> getChatRooms() {
    return chatRooms;
  }

  public void setChatRooms(List<ChatRoom> chatRooms) {
    this.chatRooms = chatRooms;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  
}