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

/**
 * This contains logic about chat. It is a bit complex and not very well written. The files that handle
 * its view side are buyer/footer.jsp, seller/footer.jsp, chat.js and files specific to chat under folder
 * jsp/chat.Css is written in customtheme.css. Chat portion is kept as separate from main portion as possible.
 * Chat module is loaded through ajax requests. Whenever a new page is loaded, corresponding footer loads bare
 * minimum chat structure. It contains ajax call logic to load relevant chat data further. On document ready of
 * any page, a function to load current state of chat module is fired from the footer.jsp of the corresponding page.
 * The state of chat is stored in session variable. Variables that define state primarily are chatpage and chatmode
 * and frompage while other session variables store the information like itemid and chatroomid etc.
 * 
 * 
 */

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceSession;
import org.atmosphere.cpr.AtmosphereResourceSessionFactory;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.FrameworkConfig;
import org.atmosphere.cpr.MetaBroadcaster;
import org.atmosphere.websocket.WebSocket;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.PersonRepository;
import com.bizbuzz.service.ChatRoomService;
import com.bizbuzz.service.ChatService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.ItemService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PropertyService;
import com.bizbuzz.utils.AtmosphereUtils;
import com.bizbuzz.dto.ChatResponseDTO;
import com.bizbuzz.dto.Message;
import com.bizbuzz.dto.SellerAddPrivateGroupResponseAjaxDTO;
import com.bizbuzz.dto.SortedMixedChatsForChatRoomDTO;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
  private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

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

  @Autowired
  ItemService itemService;
  
  @Autowired
  PropertyService propertyService;



  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test() {
    return "test";
  }

  @RequestMapping(value = "/samplechat", method = RequestMethod.GET)
  public String chatByAtmosphere(HttpSession session) {

    Person person = getPerson();
    UserLogin user = person.getUserId();
    session.setAttribute("userId",user.getId());
    session.setAttribute("chatroomId",(long)1);

    return "home";
  }

  // ~~~~~~~~~~~~~~~~~~~~~            CHAT FUNCTIONALITY WITH ATMOSPHERE FRAMWORK             ~~~~~~~~~~~~~~~~~~~~~~~

  @RequestMapping(value = "/websockets", method = RequestMethod.POST)
  @ResponseBody
  public String post(final AtmosphereResource event, @RequestBody  String request )
      throws JsonGenerationException, JsonMappingException, IOException {   
    ChatResponseDTO chatResponseDTO = new ChatResponseDTO();
    JSONObject jsonObject = JSONObject.fromObject(request);
    String message = jsonObject.get("message").toString();
    String userId = jsonObject.get("userId").toString();
    Long chatRoomId = (long)jsonObject.get("chatroomId").hashCode();   
    Long itemId = (long)jsonObject.get("itemId").hashCode();

    if(message.equals("0Open0")){
      Broadcaster b;
      if(itemId.intValue()!=0){
        b  = BroadcasterFactory.getDefault().lookup("/"+chatRoomId+"/"+itemId+"/"+userId ,true);
      }
      else{
        b = BroadcasterFactory.getDefault().lookup("/"+chatRoomId+"/"+userId ,true);
      }
      b.addAtmosphereResource(event);  
      return null;
    }
    else{

      Person person = partyManagementService.getPersonFromUsername(userId);
      ChatRoom chatRoom = chatRoomService.getChatRoomByChatRoomId(chatRoomId);
      List<Person> members = chatRoomService.getAllMembersOfChatRoomByChatRoomId(chatRoomId);

      Chat chat = new Chat();
      chat.setSender((Party)person);
      chat.setMessage(message);
      chat.setChatRoom(chatRoom);
      if(itemId.intValue()!=0){ 
        Item item = itemService.getItemByItemId(itemId);
        chat.setItem(item);
      }
      chatService.saveChat(chat);

      Date lastChatDate = chat.getCreatedAt();
      chatRoom.setUpdatedAt(lastChatDate);
      chatRoomService.saveChatRoom(chatRoom);
      String dateOfBroadcast = lastChatDate.getYear()+"-"+lastChatDate.getMonth()+"-"+lastChatDate.getDay()+" "+lastChatDate.getHours()+":"+lastChatDate.getMinutes()+":"+lastChatDate.getSeconds()+":0";
      /*     
       */
      chatResponseDTO.setChatRoomId(chatRoomId);
      chatResponseDTO.setItemId(itemId);
      chatResponseDTO.setSenderId(person.getId());
      chatResponseDTO.setMessage(message);
      chatResponseDTO.setDate(lastChatDate.getYear(), lastChatDate.getMonth(), lastChatDate.getDay(), lastChatDate.getHours(), lastChatDate.getMinutes());
      if(itemId.intValue()!=0)
        MetaBroadcaster.getDefault().broadcastTo("/"+chatRoomId+"/"+itemId+"/*", objectMapper.writeValueAsString(chatResponseDTO));
      else{ 
        //MetaBroadcaster.getDefault().broadcastTo("/"+chatRoomId+"/*", "<tr><td>" +userId + " :</td><td> " +message +"</td><td align='right'>" +dateOfBroadcast +"</td></tr>");
        for(Person member : members){
          BroadcasterFactory.getDefault().lookup("/"+chatRoomId+"/"+member.getUserId().getId()).broadcast(objectMapper.writeValueAsString(chatResponseDTO));
        }  
      }
      logger.info("Received message to broadcast: {}"+ userId +" : " +message +"           " +dateOfBroadcast);         
    }

    return  "success";
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

    // AtmosphereUtils.suspend(event);

    // final Broadcaster bc = event.getBroadcaster();

    // final int numberOfClients = bc.getAtmosphereResources().size();

    // String statusMessage = "A new Client has connected on "
    // + new Date().toString() + " (Total: " + numberOfClients + ")";

    // logger.info(statusMessage);

    // bc.broadcast(objectMapper.writeValueAsString(new StatusMessage(
    // statusMessage)));

  }

  /**
   * This function writes the logic for chat sate transitions. It tries to find using session variables and 
   * Request parameters what should be the module that needs to be returned.
   * @param session
   * @param allRequestParams
   * @return
   */
  @RequestMapping(value="/chat/controller", method = RequestMethod.GET)
  public String chatController(HttpSession session, @RequestParam Map<String, String> allRequestParams){
    String action = allRequestParams.get("chatpage");
    
    //Toggle visibility state variable
    if(action.equals("savevisibilitystate")){
      String isChatPanelVisible = allRequestParams.get("ischatpanelvisible");
      if(isChatPanelVisible.equals("true")){
        session.setAttribute("ischatpanelvisible", true);
      }
      else{
        session.removeAttribute("ischatpanelvisible");
      }
    }
    
    //Load item chat room
    if(action.equals("itemchatroom")){
      String itemId = allRequestParams.get("itemId");
      String secondPersonId = allRequestParams.get("secondPersonId");
      return "forward:/chat/showitemchatroom/secondpersonid/"+secondPersonId+"/itemid/"+itemId;
    }
    
    //Determine the existing state and redirect to correct state
    else if(action.equals("determine")){
      String chatPage = (String)session.getAttribute("chatpage");
      //first time
      if(chatPage==null){
        return "forward:/chat/showchatrooms";
      }
      else if(chatPage.equals("listofchatrooms")){
        return "forward:/chat/showchatrooms";
      }
      else if(chatPage.equals("singlechatroom")){
        Long chatroomId = (Long)session.getAttribute("chatroomid");
        return "forward:/chat/showchatroom/chatroomid/"+chatroomId;
      }
      //last state was singleitmechatroom
      else if(chatPage.equals("singleitemchatroom")){
        Long chatroomId = (Long)session.getAttribute("chatroomid");
        Long itemId = (Long)session.getAttribute("itemid");
//        if(session.getAttribute("frompage").equals("itemchatroomlist")){
//          return "redirect:/chat/showchatrooms";
//        }
        //right now which chat mode are we should be in? This is set at footer.
        if(session.getAttribute("chatmode").equals("normal")){
          return "forward:/chat/showchatrooms";
        }
        else{
          return "forward:/chat/showitemchatroom/chatroomid/"+chatroomId+"/itemid/"+itemId;
        }
      }
    }
    
    //redirect to list of chat rooms
    else if(action.equals("listofchatrooms")){
      return "forward:/chat/showchatrooms";
    }
    
    //redirect to single chat room
    else if(action.equals("singlechatroom")){
      String chatroomId = allRequestParams.get("chatroomid");
      return "forward:/chat/showchatroom/chatroomid/"+chatroomId;
    }
    
    //redirect to item chat room list
    else if(action.equals("itemchatroomlist")){
      String itemId = allRequestParams.get("itemId");
      return "forward:/chat/showchatrooms/itemid/"+itemId;
    }
    
    //implementing back button logic
    else if(action.equals("back")){
      String chatPage = (String)session.getAttribute("chatpage");
      //if chatpage has not been set in previous load. Means first page in session.
      if(chatPage==null){
        return "forward:/chat/showchatrooms";
      }
      //pushing back button while on list of chatrooms. no action.
      else if(chatPage.equals("listofchatrooms")){
        return "forward:/chat/showchatrooms";
      }
      //last state is on single chat room. Now move to list of chat rooms
      else if(chatPage.equals("singlechatroom")){
        return "forward:/chat/showchatrooms";
      }
      //last state was single item chat room
      else if(chatPage.equals("singleitemchatroom")){
        String fromPage = (String)session.getAttribute("frompage");
        //last state of singleitemchatroom was entered from itemchatroomlist. Hence now move to itehmchatroomlist
        if(fromPage.equals("itemchatroomlist")){
          return "forward:/chat/showchatrooms/itemid/"+session.getAttribute("itemid");
        }
        //last state of singleitemchatroom was entered from normal chat room. Hence now move to normal chat room
        else if(fromPage.equals("normalchatroom")){
          return "forward:/chat/showchatroom/chatroomid/"+session.getAttribute("chatroomid");
        }
      }
    }
    return "";
  }
  
  @RequestMapping(value="/chat/showchatrooms", method = RequestMethod.GET)
  public String showChatRooms(Model m, HttpSession session){
    /***Saving chat state***/
    session.setAttribute("chatpage", "listofchatrooms");
    
    Person person = getPerson();
    List<Chat> sortedChatsByTimeOfPerson = chatRoomService.getSortedChatsOfPerson(person);
    List<ChatRoom> sortedNewchatRooms = chatRoomService.getAllNewSortedChatRoomsOfPerson(person); 
    m.addAttribute("sortedchats",sortedChatsByTimeOfPerson);
    m.addAttribute("sortedChatrooms",sortedNewchatRooms);
    m.addAttribute("userid", person.getId());
    return "jsp/chat/showlistofchatrooms";
  }

  @RequestMapping(value="/chat/showchatroom/chatroomid/{chatroomid}", method = RequestMethod.GET)
  public String showChatRoom(Model m,HttpSession session,@PathVariable Long chatroomid, HttpServletRequest request) {
    /***Saving chat state***/
    session.setAttribute("chatpage", "singlechatroom");
    session.setAttribute("chatroomid", chatroomid);
    
    Person person = getPerson();
    UserLogin user = person.getUserId();

    List<Person> members = chatRoomService.getAllMembersOfChatRoomByChatRoomId(chatroomid); 
    if(members==null) return "jsp/error/usernotfound";

    boolean flag=false;
    for(Person member : members){
      if(person.getId().longValue() == member.getId().longValue()) flag=true;
    }

    if(flag){
      for(Person member : members){
        if(person.getId().longValue() != member.getId().longValue()) 
          m.addAttribute("secondperson", member);
      }  
    }
    else
      return "jsp/error/usernotfound";

    List<Chat> allChatsOfChatRoomFromDB = chatService.getAllChatsByChatRoomId(chatroomid); 
    List<Chat> normalChats = new ArrayList<Chat>();

    Map<Long, List<Chat>> itemChatMap = new LinkedHashMap<Long, List<Chat>>();
    for(Chat chat : allChatsOfChatRoomFromDB){
      if(chat.getItem() == null){
        normalChats.add(chat);
        continue;
      }
      Long itemId = chat.getItem().getId();
      if(itemChatMap.get(itemId) == null){
        itemChatMap.put(itemId, new ArrayList<Chat>());
      }
      itemChatMap.get(itemId).add(chat);
    }


    List<SortedMixedChatsForChatRoomDTO> sortedMixedChatsOfChatRoomDTOList = new ArrayList<SortedMixedChatsForChatRoomDTO>();
    List<List<Chat>> itemChatLists = new ArrayList<List<Chat>>(itemChatMap.values());
    int i=0;
    int j=0;
    int iMax = normalChats.size();
    int jMax = itemChatMap.size();
    while(i<iMax || j<jMax){
      if(i==iMax){
        SortedMixedChatsForChatRoomDTO dto = new SortedMixedChatsForChatRoomDTO(itemChatLists.get(j),null);
        sortedMixedChatsOfChatRoomDTOList.add(dto);
        j++;
        continue;
      }
      if(j==jMax){
        SortedMixedChatsForChatRoomDTO dto = new SortedMixedChatsForChatRoomDTO(null,normalChats.get(i));
        sortedMixedChatsOfChatRoomDTOList.add(dto);
        i++;
        continue;
      }
      if(normalChats.get(i).getCreatedAt().before(itemChatLists.get(j).get(0).getCreatedAt())){
        SortedMixedChatsForChatRoomDTO dto = new SortedMixedChatsForChatRoomDTO(null,normalChats.get(i));
        sortedMixedChatsOfChatRoomDTOList.add(dto);
        i++;
      }else{
        SortedMixedChatsForChatRoomDTO dto = new SortedMixedChatsForChatRoomDTO(itemChatLists.get(j),null);
        sortedMixedChatsOfChatRoomDTOList.add(dto);
        j++;
      }
    }
    m.addAttribute("allChatsOfChatroomDTOList",sortedMixedChatsOfChatRoomDTOList);
    m.addAttribute("person", person);
    m.addAttribute("userId", user.getId());
    m.addAttribute("chatroomId", chatroomid);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    return "jsp/chat/chatroom";
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~       CHAT FUNTIONALITY WITH NORMAL REQUEST-RESPONSE MODEL     ~~~~~~~~~~~~~~~~~~~~~~~

  @RequestMapping(value = "/chat/showonetoonechatmessages/{chatroomid}", method = RequestMethod.POST)
  public String showOneToOneChatMessages(@ModelAttribute("chat") Chat chat,@PathVariable Long chatroomid, Model model) {
    Person person = getPerson();
    ChatRoom chatRoom = chatRoomService.getChatRoomByChatRoomId(chatroomid);
    chat.setSender((Party)person);
    chat.setChatRoom(chatRoom);
    chatService.saveChat(chat);

    Date lastChatDate = chat.getCreatedAt();
    model.addAttribute("lastchatDate", lastChatDate);
    chatRoom.setUpdatedAt(lastChatDate);
    chatRoomService.saveChatRoom(chatRoom);
    return "redirect:/chat/showchatroom/chatroomid/{chatroomid}";
  }


  //  ~~~~~~~~~~~~~~~~~~~~~~~~~~~    ITEM CENTRIC CHAT FUNCTIONALITY WITH ATMOSPHERE FRAMWORK    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  //                                 --------------------------------------------------------

  @RequestMapping(value = "/chat/showitemchatroom/chatroomid/{chatroomid}/itemid/{itemid}/frompage/{frompage}", method = RequestMethod.GET)
  public String chatByAtmosphere(Model m,HttpSession session,@PathVariable Long chatroomid,@PathVariable Long itemid, @PathVariable String frompage) {
    /***Saving chat state***/
    session.setAttribute("chatpage", "singleitemchatroom");
    session.setAttribute("chatroomid", chatroomid);
    session.setAttribute("itemid", itemid);
    session.setAttribute("frompage", frompage);
    
    Person person = getPerson();
    UserLogin user = person.getUserId(); 

    List<Person> members = chatRoomService.getAllMembersOfChatRoomByChatRoomId(chatroomid); 
    if(members==null) return "jsp/error/usernotfound";

    boolean flag=false;
    for(Person member : members){
      if(person.getId().longValue() == member.getId().longValue()) flag=true;
    }

    if(flag){
      for(Person member : members){
        if(person.getId().longValue() != member.getId().longValue()) 
          m.addAttribute("secondperson", member);
      }  
    }
    else
      return "jsp/error/usernotfound";

    List<Chat> itemChatsFromDatabase  = chatService.getAllChatsByChatRoomIdAndItemId(chatroomid,itemid);
    
    Item item = itemService.getItemByItemId(itemid);
    m.addAttribute("item",item);
    m.addAttribute("chatsOfItem", itemChatsFromDatabase);
    m.addAttribute("person", person);
    m.addAttribute("userId", user.getId());
    m.addAttribute("chatroomId", chatroomid);
    m.addAttribute("itemId", itemid);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    return "jsp/chat/itemchatroom";
  }
  
  @RequestMapping(value = "/chat/showitemchatroom/secondpersonid/{secondPersonId}/itemid/{itemid}", method = RequestMethod.GET)
  public String itemChatByItemIdAndSecondPersonId(Model m,HttpSession session,@PathVariable Long secondPersonId,@PathVariable Long itemid) {
    Person person = getPerson();
    UserLogin user = person.getUserId(); 
    
    ChatRoom chatRoom = chatRoomService.getChatRoomByMembers(person.getId(), secondPersonId);
    Long chatroomid = chatRoom.getId();
    
    List<Person> members = chatRoomService.getAllMembersOfChatRoomByChatRoomId(chatroomid); 
    if(members==null) return "jsp/error/usernotfound";

    boolean flag=false;
    for(Person member : members){
      if(person.getId().longValue() == member.getId().longValue()) flag=true;
    }

    if(flag){
      for(Person member : members){
        if(person.getId().longValue() != member.getId().longValue()) 
          m.addAttribute("secondperson", member);
      }  
    }
    else
      return "jsp/error/usernotfound";

    List<Chat> itemChatsFromDatabase  = chatService.getAllChatsByChatRoomIdAndItemId(chatroomid,itemid);
    Item item = itemService.getItemByItemId(itemid);
    m.addAttribute("item",item);
    m.addAttribute("chatsOfItem", itemChatsFromDatabase);
    m.addAttribute("person", person);
    m.addAttribute("userId", user.getId());
    m.addAttribute("chatroomId", chatroomid);
    m.addAttribute("itemId", itemid);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    return "jsp/chat/itemchatroom";
  }

  @RequestMapping(value = "/chat/showchatrooms/itemid/{itemid}", method = RequestMethod.GET)
  public String showItemChatRooms(Model m, @PathVariable("itemid") Long itemId, HttpSession session){
    /***Saving chat state***/
    session.setAttribute("itemid", itemId);
    
    Person person = getPerson();
    List<Chat> sortedChatsByTimeOfPerson = chatRoomService.getSortedItemChatsOfPerson(person.getId(), itemId);
    List<ChatRoom> sortedNewchatRooms = chatRoomService.getAllNewSortedChatRoomsOfPerson(person); 
    m.addAttribute("sortedchats",sortedChatsByTimeOfPerson);
    m.addAttribute("sortedChatrooms",sortedNewchatRooms);
    m.addAttribute("userid", person.getId());
    m.addAttribute("itemId", itemId);
    return "jsp/chat/itemchatroomlist";
  }
  
  public Person getPerson() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }

}
