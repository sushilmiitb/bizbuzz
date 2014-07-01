package com.bizbuzz.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizbuzz.dto.PrivateGroupFormDTO;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.PartyManagementService;

@Controller
public class SellerController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  ConnectionService connectionService;
  
  @Autowired
  PartyManagementService partyManagementService;

  @RequestMapping(value="/seller/groupmanagement", method = RequestMethod.GET)
  public String groupManagement(Model m){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    Person person = partyManagementService.getPersonFromUsername(username);
    List<PrivateGroup> privateGroupList = connectionService.getListOfPrivateGroupsFromPerson(person);
    m.addAttribute("privateGroups", privateGroupList);
    PrivateGroup privateGroup = new PrivateGroup();
    m.addAttribute("privateGroupForm", privateGroup);
    return "jsp/seller/viewgroup";
  }  
  
  @RequestMapping(value="/seller/addgroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public PrivateGroupFormDTO addGroup(@RequestBody PrivateGroupFormDTO privateGroupDTO){
    PrivateGroup privateGroup = new PrivateGroup();
    privateGroup.setPrivateGroupName(privateGroupDTO.getPrivateGroupName());
    partyManagementService.savePrivateGroup(privateGroup);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    Person person = partyManagementService.getPersonFromUsername(username);
    connectionService.createPersonOwnerPrivateGroupConnection(person, privateGroup);
    return privateGroupDTO;
  }
}
