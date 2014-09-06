package com.bizbuzz.dto;

import java.util.List;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.Item;

public class SortedMixedChatsForChatRoomDTO {

 // private Item item;
  private Chat chat;
  private List<Chat> listOfChats;
 
  public SortedMixedChatsForChatRoomDTO(List<Chat> listOfChats,Chat chat) {
    super();
    this.chat = chat;
    this.listOfChats = listOfChats;
  }
  public List<Chat> getListOfChats() {
    return listOfChats;
  }
  public void setListOfChats(List<Chat> listOfChats) {
    this.listOfChats = listOfChats;
  }
  /*public SortedMixedChatsForChatRoomDTO(Item item, Chat chat) {
    super();
    this.item = item;
    this.chat = chat;
  }
  
  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }
*/
  public Chat getChat() {
    return chat;
  }
  public void setChat(Chat chat) {
    this.chat = chat;
  }
}
