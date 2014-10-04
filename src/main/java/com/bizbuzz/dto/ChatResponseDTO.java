package com.bizbuzz.dto;

public class ChatResponseDTO {
  private Long senderId;
  private Long receiverId;
  private Long chatId;
  private Long chatRoomId;
  private String message;
  private Long itemId;
  private String errorMsg;
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minute;
  private int seconds;
  

  public void setDate(int year, int month, int day, int hour, int minute, int seconds){
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.seconds = seconds;
  }
  
  public Long getSenderId() {
    return senderId;
  }
  public void setSenderId(Long senderId) {
    this.senderId = senderId;
  }
  public Long getReceiverId() {
    return receiverId;
  }
  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }
  public Long getChatId() {
    return chatId;
  }
  public void setChatId(Long messageId) {
    this.chatId = messageId;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Long getItemId() {
    return itemId;
  }
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  public String getErrorMsg() {
    return errorMsg;
  }
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  public int getYear() {
    return year;
  }
  public void setYear(int year) {
    this.year = year;
  }
  public int getMonth() {
    return month;
  }
  public void setMonth(int month) {
    this.month = month;
  }
  public int getDay() {
    return day;
  }
  public void setDay(int day) {
    this.day = day;
  }
  public int getHour() {
    return hour;
  }
  public void setHour(int hour) {
    this.hour = hour;
  }
  public int getMinute() {
    return minute;
  }
  public void setMinute(int minute) {
    this.minute = minute;
  }
  public int getSeconds() {
    return seconds;
  }

  public void setSeconds(int seconds) {
    this.seconds = seconds;
  }
  public Long getChatRoomId() {
    return chatRoomId;
  }
  public void setChatRoomId(Long chatRoomId) {
    this.chatRoomId = chatRoomId;
  }
  
}
