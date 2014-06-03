package com.bizbuzz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ChatRoom implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="sender_id", referencedColumnName="id")
  private Party sender;
  
  @ManyToMany
  @JoinTable(
      name="chatroom_recipeint",
      joinColumns={@JoinColumn(name="chatroom_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="recipient_id", referencedColumnName="id")})
  private List<Party> recipients;
  
  @OneToMany(mappedBy="chatRoom")
  private List<Chat> chats;
  
  /**
   * setters and getters
   */
  
  public Party getSender() {
    return sender;
  }

  public void setSender(Party sender) {
    this.sender = sender;
  }

  public List<Party> getRecipients() {
    return recipients;
  }

  public void setRecipients(List<Party> recipients) {
    this.recipients = recipients;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public void setChats(List<Chat> chats) {
    this.chats = chats;
  }

}
