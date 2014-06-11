package com.bizbuzz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeInfo;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeInfo(
		use=JsonTypeInfo.Id.CLASS,
		include=JsonTypeInfo.As.PROPERTY,
		property="@class")
public class Messages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Collection<Message> messages = new ArrayList<Message>();
	
	@XmlAttribute
	private Long lastMessageId;

	public void addMessage(Message message){
		messages.add(message);
	}
	
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	public Long getLastMessageId() {
		return lastMessageId;
	}

	public void setLastMessageId(Long lastMessageId) {
		this.lastMessageId = lastMessageId;
	}



	@XmlAttribute
	public String getLatestTweetIdAsString() {
		return String.valueOf(lastMessageId);
	}

	
}
