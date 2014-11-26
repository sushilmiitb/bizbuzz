package com.bizbuzz.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizbuzz.dto.SellerAddConnectionRequestAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionResponseAjaxDTO;
import com.bizbuzz.form.validator.SellerValidator;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ChatRoomService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.ItemService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PropertyService;
import com.bizbuzz.utils.SmsSender;

@Controller
public class BuyerController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  PartyManagementService partyManagementService;
  @Autowired
  CategoryService categoryService;
  @Autowired
  PropertyService propertyService;
  @Autowired
  ItemService itemService;
  @Autowired
  ConnectionService connectionService;
  @Autowired
  ChatRoomService chatRoomService;
  @Autowired
  SellerValidator sellerValidator;
  
  @RequestMapping(value={"/buyer", "/buyer/home"}, method = RequestMethod.GET)
  public String buyerHome(){
    return "jsp/buyer/home";
  }
  
  @RequestMapping(value="/buyer/viewcontacts", method = RequestMethod.GET)
  public String viewBuyerContacts(Model m){
    Person buyer = getBuyer();
   // List<PrivateGroup> privateGroupList = connectionService.getPrivateGroupsByGroupOnwer(buyer);
   // m.addAttribute("privateGroups", privateGroupList);
    PrivateGroup privateGroup = new PrivateGroup();
    m.addAttribute("privateGroupForm", privateGroup);
    
    List<Connection> allConnections = connectionService.getAllBuyersConnection(buyer);
    m.addAttribute("connectionList", allConnections);
 //   m.addAttribute("privateGroupList", privateGroupList);
 
    return "jsp/buyer/viewcontacts";
  }
  
  @RequestMapping(value="/buyer/addconnection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SellerAddConnectionResponseAjaxDTO addConnection(@RequestBody SellerAddConnectionRequestAjaxDTO request){
    Map<String, String> errors;
    SellerAddConnectionResponseAjaxDTO ajaxReply = new SellerAddConnectionResponseAjaxDTO();
    Person buyer = getBuyer();
    Person toPerson = partyManagementService.getPersonFromPhoneNumberUsername(request.getUserId());
    
    if(toPerson == null){
      SmsSender smsSender = new SmsSender(request.getUserId(),"Hello");
      smsSender.sendSms();
      return ajaxReply;
    }
    
    errors = sellerValidator.validateAddConnection(toPerson,buyer);
    if(errors.size()>0){
      ajaxReply.setErrors(errors);
      return ajaxReply;
    }
    
    /**
     * change other functions also to use generic createconnection
     */
    connectionService.createConnection(toPerson,buyer, ConnectionType.SELLER_BUYER);
/*   
    PrivateGroup privateGroup = null;
    if(request.getGroupId()!=-1){
      privateGroup = partyManagementService.getPrivateGroup(request.getGroupId());
      connectionService.createConnection(privateGroup, toPerson, ConnectionType.GROUP_MEMBERS);
    }
*/    
 
 //   ajaxReply.addDetails(toPerson, privateGroup);
    return ajaxReply;
  }
 
  @RequestMapping(value="/buyer/viewconnection/{sellerId}", method = RequestMethod.GET)
  public String getSingleBuyerConnection(@PathVariable Long sellerId, Model m){
    Person buyer = getBuyer();
    Person seller = connectionService.getSellerByBuyerAndSellerId(buyer, sellerId);
    if(seller==null){
      return "redirect:/buyer/viewcontacts";
    }
    
//    PrivateGroup privateGroup = connectionService.getPrivateGroupByGroupOwnerAndGroupMember(buyer, seller);
    m.addAttribute("seller", seller);
   // m.addAttribute("privateGroup", privateGroup);
  //  List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
   // m.addAttribute("privateGroupList", privateGroups);
    return "jsp/buyer/viewsingleconnection";
  }
  
  @RequestMapping(value="/buyer/deleteconnection/{personId}")
  public String deleteConnection(@PathVariable Long personId){
//    List<String> errors = new ArrayList<String>();
    Person buyer = getBuyer();
    Person toPerson = partyManagementService.getPerson(personId);
    if(toPerson == null){
      /**
       * Person has not registered. Add code to ask him to register.
       */
      return "redirect:/buyer/viewcontacts";
    }
    connectionService.deleteConnection(buyer, toPerson);
    
    PrivateGroup privateGroup = connectionService.getPrivateGroupByGroupOwnerAndGroupMember(buyer, toPerson);
    if(privateGroup != null){
      connectionService.deleteConnection(privateGroup, toPerson);
    }
//=====================================================                         Delete ChatRoom of the connection also    
   // ChatRoom chatroom = chatRoomService.getChatRoomByMembers(seller.getId(), toPerson.getId());
   // chatService.deleteAllChatByChatRoomId(chatroom.getId());
    //chatRoomService.deleteChatRoom(chatroom.getId());
    
    return "redirect:/buyer/viewcontacts";
  }
 
  @RequestMapping(value="/buyer/viewcategory/seller/{sellerId}/category/{categoryId}", method=RequestMethod.GET)
  public String viewCategory(Model m, @PathVariable Long categoryId, @PathVariable Long sellerId){
    CategoryTree categoryTree = null;
    Person buyer = getBuyer();
    Person seller = connectionService.getSellerByBuyerAndSellerId(buyer, sellerId);
    if(categoryId==null || categoryId==-1){
      categoryTree = buyer.getCategoryRoot();
    }
    else{
      categoryTree = categoryService.getCategory(categoryId);
    }
    Boolean hasProduct = categoryTree.getHasProduct();
    String parentCategoryName = categoryTree.getCategoryName();
    if(hasProduct){
      //PropertyMetadata propertyMetadata = categoryService.getPropertyMetadata(seller, depth, categoryId);
      return "redirect:/buyer/viewproduct/category/"+categoryTree.getId();
    }
    else{
      //List<CategoryTree> categories = categoryService.getCategories(seller, depth, categoryId);
      List<CategoryTree> categories = categoryService.getAllCategories(categoryTree.getId(), seller.getId());
      m.addAttribute("rootDir", propertyService.getImageDir());
      m.addAttribute("sizeDir", "360");
      m.addAttribute("imageExtn", "jpg");
      m.addAttribute("categoryList", categories);
      m.addAttribute("parentCategoryName", parentCategoryName);
      m.addAttribute("sellerid", sellerId);
      //m.addAttribute("depth", depth+1);
      return "jsp/buyer/viewcategory";
    }
  }
  
/*  @RequestMapping(value="/buyer/viewseller/category/{categoryId}", method=RequestMethod.GET)
  public String viewSellerOfACategory(Model m, @PathVariable Long categoryId){
    Person buyer = getBuyer();
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    List<Person> sellers = itemService.getSellersByBuyerIdAndCategoryIdOrderByLatestItemUpload(buyer, categoryTree);
    List<Company> companies = itemService.getCompaniesOfSellersByBuyerIdAndCategoryIdOrderByLatestItemUpload(buyer, categoryTree);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("category", categoryTree);
    m.addAttribute("sellers", sellers);
    m.addAttribute("companies", companies);
    return "jsp/buyer/viewseller";
  }*/
  
  @RequestMapping(value="/buyer/viewcategory/viewsellers", method=RequestMethod.GET)
  public String viewProductSellers(Model m){
    Person buyer = getBuyer();
    List<Person> sellers = itemService.getSellersByBuyerIdOrderByLatestItemUpload(buyer);
    List<Company> companies = itemService.getCompaniesOfSellersByBuyerIdOrderByLatestItemUpload(buyer);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("sellers", sellers);
    m.addAttribute("companies", companies);
    return "jsp/buyer/viewseller";
  }
  
  @RequestMapping(value="/buyer/viewproduct/seller/{sellerId}/category/{categoryId}", method=RequestMethod.GET)
  public String viewProduct(Model m, @PathVariable Long categoryId, @PathVariable Long sellerId){
    Person buyer = getBuyer();
    Person seller = partyManagementService.getPerson(sellerId);
    m.addAttribute("seller", seller);
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    m.addAttribute("parentCategory", categoryTree);
    
    List<Item> items = itemService.getItemsByCategoryIdAndOwnerAndBuyerWithImageModels(categoryId, sellerId, buyer.getId());
    
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("items", items);
    return "jsp/buyer/viewproduct";
  }
  
  @RequestMapping(value="/buyer/viewproduct/item/{itemId}/seller/{sellerId}", method=RequestMethod.GET)
  public String viewItem(@PathVariable Long itemId, @PathVariable Long sellerId, Model m){
    Person buyer =  getBuyer();
    
    Person seller = partyManagementService.getPerson(sellerId);
    m.addAttribute("seller", seller);
    
    Item item = itemService.getItemByItemIdAndOwnerAndBuyerWithImageModelsAndPropertyValues(itemId, sellerId, buyer.getId());
    
    PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(item.getItemCategory().getId());
    m.addAttribute("propertyMetadata", propertyMetadata);
    
    Map<Long, PropertyValue> propertyValueMap = propertyService.getPropertyValuesMappedByPropertyField(item.getPropertyValues());
    m.addAttribute("propertyValueMap", propertyValueMap);
    
    Map<Long, ImageModel> valueImageModelMap = propertyService.getImageModelValuesMappedByImageModelMeta(item.getImageModels());
    m.addAttribute("valueImageModelMap", valueImageModelMap);
    
    m.addAttribute("item", item);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    
    return "jsp/buyer/viewitem";
  }
  
  @RequestMapping(value="/buyer/viewfullimage/{id}/extn/{extn}")
  public String viewProduct(@PathVariable String id, @PathVariable String extn, Model m){
    int width=600;
    int height=600*4/3;
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "600");
    m.addAttribute("id", id);
    m.addAttribute("extn", extn);
    m.addAttribute("width", width);
    m.addAttribute("height", height);
    return "jsp/common/viewfullimage";
  }
  
  public Person getBuyer(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
}
