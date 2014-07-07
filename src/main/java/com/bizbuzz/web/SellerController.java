package com.bizbuzz.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
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

import com.bizbuzz.dto.PrivateGroupFormDTO;
import com.bizbuzz.dto.SellerAddConnectionRequestAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionResponseAjaxDTO;
import com.bizbuzz.form.validator.SellerValidator;
import com.bizbuzz.model.Connection.ConnectionType;
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
  
  @Autowired
  SellerValidator sellerValidator;
  
  @Autowired
  MessageSource messageSource;

  @RequestMapping(value="/seller/viewgroup", method = RequestMethod.GET)
  public String viewAllGroup(Model m){
    Person person = getSeller();
    List<PrivateGroup> privateGroupList = connectionService.getListOfPrivateGroupsFromPerson(person);
    m.addAttribute("privateGroups", privateGroupList);
    PrivateGroup privateGroup = new PrivateGroup();
    m.addAttribute("privateGroupForm", privateGroup);
    return "jsp/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/viewgroup/{groupname}", method = RequestMethod.GET)
  public String viewAGroup(Model m, @PathVariable final String groupname){
    Person person = getSeller();
    PrivateGroup privateGroup = connectionService.getPrivateGroupFromPerson(person, groupname);
    m.addAttribute("privateGroup", privateGroup);
    return "jsp/seller/viewsinglegroup";
  }
  
  @RequestMapping(value="/seller/editgroup/{oldGroupName}", method = RequestMethod.POST)
  public String editAGroup(@PathVariable final  String oldGroupName, @ModelAttribute("privateGroup") PrivateGroup updatedPrivateGroup, Model m){
    Person person = getSeller();
    List<String> errors = sellerValidator.validateAddPrivateGroup(updatedPrivateGroup, person);
    if(errors.size()>0){
      m.addAttribute("privateGroup", updatedPrivateGroup);
      m.addAttribute("errors", errors);
      return "jsp/seller/viewsinglegroup";
    }
    PrivateGroup oldPrivateGroup = connectionService.getPrivateGroupFromPerson(person, oldGroupName);
    partyManagementService.updatePrivateGroup(oldPrivateGroup, updatedPrivateGroup);
    return "redirect:/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/deletegroup/{groupName}", method = RequestMethod.GET)
  public String deleteAGroup(@PathVariable final String groupName){
    Person person = getSeller();
    PrivateGroup privateGroup = connectionService.getPrivateGroupFromPerson(person, groupName);
    connectionService.deletePersonOwnerPrivateGroupConnection(person, privateGroup);
    partyManagementService.deletePrivateGroup(privateGroup);
    return "redirect:/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/addgroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public PrivateGroupFormDTO addPrivateGroup(@RequestBody PrivateGroupFormDTO privateGroupDTO){

    //SellerValidator sellerValidator = new SellerValidator();
    PrivateGroup privateGroup = new PrivateGroup();
    privateGroup.setPrivateGroupName(privateGroupDTO.getPrivateGroupName());
    Person person = getSeller();
    List<String> errors = sellerValidator.validateAddPrivateGroup(privateGroup, person);
    if(errors.size()>0){
      privateGroupDTO.setErrors(errors);
      return privateGroupDTO;
    }
    partyManagementService.savePrivateGroup(privateGroup);
    
    connectionService.createPersonOwnerPrivateGroupConnection(person, privateGroup);
    return privateGroupDTO;
  }
  
  @RequestMapping(value="/seller/viewconnection", method = RequestMethod.GET)
  public String getConnections(Model m){
    Person seller = getSeller();
    List<Person> allConnections = connectionService.getAllSellerConnections(seller);
    m.addAttribute("connectionList", allConnections);
    List<PrivateGroup> privateGroups = connectionService.getListOfPrivateGroupsFromPerson(seller);
    m.addAttribute("privateGroupList", privateGroups);
    return "jsp/seller/viewconnection";
  }
  
  @RequestMapping(value="/seller/addconnection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SellerAddConnectionResponseAjaxDTO addConnection(@RequestBody SellerAddConnectionRequestAjaxDTO request){
    Map<String, String> errors;
    SellerAddConnectionResponseAjaxDTO ajaxReply = new SellerAddConnectionResponseAjaxDTO();
    Person seller = getSeller();
    Person toPerson = partyManagementService.getPersonFromPhoneNumberUsername(request.getPhoneNumber());
    if(toPerson == null){
      /**
       * Person hasnot registered. Add code to ask him register him. Till then handle it in the validation.
       */
    }
    
    errors = sellerValidator.validateAddConnection(seller, toPerson);
    if(errors.size()>0){
      ajaxReply.setErrors(errors);
      return ajaxReply;
    }
    
    /**
     * change other functions also to use generic createconnection
     */
    connectionService.createConnection(seller, toPerson, ConnectionType.SELLER_BUYER);
    if(request.getGroupId()!=-1){
      PrivateGroup privateGroup = partyManagementService.getPrivateGroup(request.getGroupId());
      connectionService.createConnection(privateGroup, toPerson, ConnectionType.GROUP_MEMBERS);
    }
    ajaxReply.addDetails(toPerson);
    return ajaxReply;
  }
  
  @RequestMapping(value="/seller/deleteconnection/{personId}")
  public String deleteConnection(@PathVariable Long personId){
    List<String> errors = new ArrayList<String>();
    Person seller = getSeller();
    Person toPerson = partyManagementService.getPerson(personId);
    if(toPerson == null){
      /**
       * Person has not registered. Add code to ask him to register.
       */
      return "redirect:/seller/viewconnection";
    }
    connectionService.deleteConnection(seller, toPerson);
    
    PrivateGroup privateGroup = connectionService.getPrivateGroupByGroupOwnerAndGroupMember(seller, toPerson);
    if(privateGroup != null){
      connectionService.deleteConnection(privateGroup, toPerson);
    }
    return "redirect:/seller/viewconnection";
  }
  
  public Person getSeller(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
}
  