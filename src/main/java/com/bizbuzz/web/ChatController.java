/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bizbuzz.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereResource;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.service.ChatRoomService;
import com.bizbuzz.service.ChatService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.utils.AtmosphereUtils;
import com.bizbuzz.dto.Message;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Main controller.
 * 
 * @author Gunnar Hillert
 * @since 1.0
 * 
 */
@Controller
public class ChatController {

  private static final Logger logger = LoggerFactory
      .getLogger(ChatController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  ChatService chatService;

  @Autowired
  PartyManagementService partyManagementService;

  @Autowired
  ConnectionService connectionService;

  @Autowired
  ChatRoomService chatRoomService;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test() {
    return "test";
  }

  @RequestMapping(value = "/websockets", method = RequestMethod.POST)
  @ResponseBody
  public void post(final AtmosphereResource event, @RequestBody String msg)
      throws JsonGenerationException, JsonMappingException, IOException {

    logger.info("Received message to broadcast: {}", msg);
    event.getBroadcaster().getAtmosphereResources().size();
    event.getBroadcaster().broadcast(msg);
  }

  /**
   * Responsible for suspending the {@link HttpServletResponse} and executing a
   * broadcasts periodically.
   * 
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonGenerationException
   */
@RequestMapping(value = "/websockets", method = RequestMethod.GET)
@ResponseBody
public void websockets(final AtmosphereResource event)
      throws JsonGenerationException, JsonMappingException, IOException {

    AtmosphereUtils.suspend(event);

    // final Broadcaster bc = event.getBroadcaster();

    // final int numberOfClients = bc.getAtmosphereResources().size();

    // String statusMessage = "A new Client has connected on "
    // + new Date().toString() + " (Total: " + numberOfClients + ")";

    // logger.info(statusMessage);

    // bc.broadcast(objectMapper.writeValueAsString(new StatusMessage(
    // statusMessage)));

  }

@RequestMapping(value = "/chat/showchatrooms", method = RequestMethod.GET)
public String showChatRooms(Model m) {
    Person person = getPerson();
    List<ChatRoom> chatRoomsOfLoggedInPerson = chatRoomService.getSortedChatRoomsOfPerson(person.getId());
    m.addAttribute("chatrooms", chatRoomsOfLoggedInPerson);
    return "jsp/chat/showlistofchatrooms";
  }

@RequestMapping(value="/chat/showchatroom/chatroomid/{chatroomid}", method = RequestMethod.GET)
public String showChatRoom(Model m,@PathVariable long chatroomid) {
  Person loggedInPerson = getPerson();
  Person anotherPerson = chatRoomService.getPersonByChatRoomId(chatroomid,loggedInPerson.getId()); 
  if(anotherPerson==null) return "jsp/error/usernotfound";
  
   List<Chat> chatFromDb = chatService.getAllChats(chatroomid);
   m.addAttribute("chats", chatFromDb);
   m.addAttribute("chat", new Chat());
   m.addAttribute("person", anotherPerson);
   m.addAttribute("chatroomid", chatroomid);
   return "jsp/chat/onetoonechat";
  }


@RequestMapping(value = "chat/showonetoonechatmessages/{chatroomid}", method = RequestMethod.POST)
public String showOneToOneChatMessages(@ModelAttribute("chat") Chat chat,@PathVariable Long chatroomid, Model model) {
  Person person = getPerson();
  ChatRoom chatRoom = chatRoomService.getChatRoom(chatroomid);
  chat.setSender(partyManagementService.getParty(person.getId()));
  chat.setChatRoom(chatRoom);
  chatService.saveMessage(chat);
  Date lastChatDate = chat.getCreatedAt();
  model.addAttribute("lastchatDate", lastChatDate);
  chatRoom.setUpdatedAt(lastChatDate);
  chatRoomService.saveChatroomMembers(chatRoom);
    return "redirect:/chat/showchatroom/chatroomid/{chatroomid}";
  }
/*

*/
public Person getPerson() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }

}
