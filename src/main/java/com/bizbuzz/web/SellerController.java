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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizbuzz.dto.SellerAddPrivateGroupResponseAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionRequestAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionResponseAjaxDTO;
import com.bizbuzz.dto.SellerEditConnectionChangeGroupRequestAjaxDTO;
import com.bizbuzz.form.validator.SellerValidator;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.service.CategoryService;
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
  
  @Autowired
  CategoryService categoryService;

  @RequestMapping(value="/seller/viewgroup", method = RequestMethod.GET)
  public String viewAllGroup(Model m){
    Person person = getSeller();
    List<PrivateGroup> privateGroupList = connectionService.getPrivateGroupsByGroupOnwer(person);
    m.addAttribute("privateGroups", privateGroupList);
    PrivateGroup privateGroup = new PrivateGroup();
    m.addAttribute("privateGroupForm", privateGroup);
    return "jsp/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/viewgroup/{groupId}", method = RequestMethod.GET)
  public String viewAGroup(Model m, @PathVariable final Long groupId){
    Person person = getSeller();
    PrivateGroup privateGroup = connectionService.getPrivateGroupByPersonAndPrivateGroupId(person, groupId);
    if(privateGroup==null){
      return "redirect:/seller/viewgroup";
    }
    List<Person> groupMembers = connectionService.getGroupMembersByPrivateGroup(privateGroup);
    m.addAttribute("groupMembers", groupMembers);
    m.addAttribute("privateGroup", privateGroup);
    return "jsp/seller/viewsinglegroup";
  }
  
  @RequestMapping(value="/seller/addgroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SellerAddPrivateGroupResponseAjaxDTO addPrivateGroup(@RequestBody SellerAddPrivateGroupResponseAjaxDTO privateGroupDTO){

    //SellerValidator sellerValidator = new SellerValidator();
    PrivateGroup privateGroup = new PrivateGroup();
    privateGroup.setPrivateGroupName(privateGroupDTO.getPrivateGroupName());
    Person person = getSeller();
    Map<String,String> errors = sellerValidator.validateAddPrivateGroup(privateGroup, person);
    if(errors.size()>0){
      privateGroupDTO.setErrors(errors);
      return privateGroupDTO;
    }
    partyManagementService.savePrivateGroup(privateGroup);
    connectionService.createConnection(person, privateGroup, ConnectionType.GROUPOWNER_GROUP);
    return privateGroupDTO;
  }

  @RequestMapping(value="/seller/editgroup/{oldGroupId}", method = RequestMethod.POST)
  public String editAGroup(@PathVariable final  Long oldGroupId, @ModelAttribute("privateGroup") PrivateGroup updatedPrivateGroup, Model m){
    Person person = getSeller();
    Map<String, String> errors = sellerValidator.validateAddPrivateGroup(updatedPrivateGroup, person);
    if(errors.size()>0){
      m.addAttribute("privateGroup", updatedPrivateGroup);
      m.addAttribute("errors", errors);
      return "jsp/seller/viewsinglegroup";
    }
    PrivateGroup oldPrivateGroup = connectionService.getPrivateGroupByPersonAndPrivateGroupId(person, oldGroupId);
    partyManagementService.updatePrivateGroup(oldPrivateGroup, updatedPrivateGroup);
    return "redirect:/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/deletegroup/{groupId}", method = RequestMethod.GET)
  public String deleteAGroup(@PathVariable final Long groupId){
    Person person = getSeller();
    PrivateGroup privateGroup = connectionService.getPrivateGroupByPersonAndPrivateGroupId(person, groupId);
    if(privateGroup==null){
      return "redirect:/seller/viewgroup";
    }
    connectionService.deleteConnection(person, privateGroup);
    connectionService.deletePrivateGroupGroupMembersConnection(privateGroup);
    partyManagementService.deletePrivateGroup(privateGroup);
    return "redirect:/seller/viewgroup";
  }
  
  @RequestMapping(value="/seller/viewconnection", method = RequestMethod.GET)
  public String getConnections(Model m){
    Person seller = getSeller();
    List<Person> allConnections = connectionService.getAllSellersConnections(seller);
    m.addAttribute("connectionList", allConnections);
    List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
    m.addAttribute("privateGroupList", privateGroups);
    return "jsp/seller/viewconnection";
  }
  
  @RequestMapping(value="/seller/viewconnection/{buyerId}", method = RequestMethod.GET)
  public String getSingleConnection(@PathVariable Long buyerId, Model m){
    Person seller = getSeller();
    Person buyer = connectionService.getBuyerBySellerAndBuyerId(seller, buyerId);
    if(buyer==null){
      return "redirect:/seller/viewconnection";
    }
    PrivateGroup privateGroup = connectionService.getPrivateGroupByGroupOwnerAndGroupMember(seller, buyer);
    m.addAttribute("buyer", buyer);
    m.addAttribute("privateGroup", privateGroup);
    List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
    m.addAttribute("privateGroupList", privateGroups);
    return "jsp/seller/viewsingleconnection";
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
  
  @RequestMapping(value="/seller/editconnection/changegroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SellerAddConnectionResponseAjaxDTO editConnectionChangeGroup(@RequestBody SellerEditConnectionChangeGroupRequestAjaxDTO request){
    Map<String, String> errors;
    SellerAddConnectionResponseAjaxDTO ajaxReply = new SellerAddConnectionResponseAjaxDTO();
    Person seller = getSeller();
    Person toPerson = partyManagementService.getPerson(request.getPersonId());
    if(toPerson == null){
      /**
       * Person hasnot registered. Add code to ask him register him. Till then handle it in the validation.
       */
    }
    
    errors = sellerValidator.validateEditConnectionChangeGroup(seller, toPerson);
    if(errors.size()>0){
      ajaxReply.setErrors(errors);
      return ajaxReply;
    }
    
    /**
     * change other functions also to use generic createconnection
     */
    PrivateGroup oldGroup = connectionService.getPrivateGroupByGroupOwnerAndGroupMember(seller, toPerson);
    if(oldGroup!=null){
      connectionService.deleteConnection(oldGroup, toPerson);
    }
    if(request.getGroupId()!=-1){
      PrivateGroup newPrivateGroup = partyManagementService.getPrivateGroup(request.getGroupId());
      connectionService.createConnection(newPrivateGroup, toPerson, ConnectionType.GROUP_MEMBERS);
    }
    ajaxReply.addDetails(toPerson);
    return ajaxReply;
  }
  
//  @RequestMapping(value="/seller/uploadproduct/category/{categoryId}", method=RequestMethod.GET)
//  public String viewCategoryForUpload(Model m, @PathVariable Long categoryId, @RequestParam("depth") Integer depth, @RequestParam("leaf") Boolean isLeaf, @RequestParam("parentCategoryName") String parentCategoryName){
//    Person seller = getSeller();
//    if(isLeaf){
//      List<PropertyMetadata> properties = categoryService.getPropertyMetadatas(seller, depth, categoryId);
//      Map<String, Map<String, Map<String, PropertyMetadata>>> propertyMap = categoryService.organizeMetadata(properties);
//      m.addAttribute("propertyMap", propertyMap);
//      m.addAttribute("parentCategoryName", parentCategoryName);
//      return "jsp/seller/viewuploadproduct";
//    }
//    else{
//      List<CategoryTree> categories = categoryService.getCategories(seller, depth, categoryId);
//      m.addAttribute("categoryList", categories);
//      m.addAttribute("parentCategoryName", parentCategoryName);
//      m.addAttribute("depth", depth+1);
//      return "jsp/seller/viewuploadcategory";
//    }
//  }
  
  public Person getSeller(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
  
}
  