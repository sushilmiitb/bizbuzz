package com.bizbuzz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ChatRoom implements Serializable{

  public Date getCreatedAt() {
    return createdAt;
  }

  public Long getId() {
    return id;
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
    
  @ManyToMany(fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinTable(
      name="chatroom_members",
      joinColumns={@JoinColumn(name="chatroom_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="member_id", referencedColumnName="id")})
  private List<Party> members;
  
  @OneToMany(mappedBy="chatRoom")
  private List<Chat> chats;
  
  
  private Date createdAt;
  
  private Date updatedAt;
  
  /**
   * setters and getters
   */

  public List<Party> getMembers() {
    return members;
  }

  public void setMembers(List<Party> members) {
    this.members = members;
  }

  public List<Chat> getChats() {
    return chats;
  }

  public void setChats(List<Chat> chats) {
    this.chats = chats;
  }
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }

  
  protected void onUpdate() {
    updatedAt = new Date();
  }
}
