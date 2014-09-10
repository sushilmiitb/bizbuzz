package com.bizbuzz.web;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.form.HiddenInputTag;

import com.bizbuzz.dto.SellerAddPrivateGroupResponseAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionRequestAjaxDTO;
import com.bizbuzz.dto.SellerAddConnectionResponseAjaxDTO;
import com.bizbuzz.dto.SellerEditConnectionChangeGroupRequestAjaxDTO;
import com.bizbuzz.dto.ProductDetailDTO;
import com.bizbuzz.dto.SendSmsRequestAjaxDTO;
import com.bizbuzz.form.validator.SellerValidator;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Chat;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyField;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ChatRoomService;
import com.bizbuzz.service.ChatService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.ItemService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PropertyService;
import com.bizbuzz.utils.SmsSender;

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
  
  @Autowired
  ItemService itemService;

  @Autowired
  PropertyService propertyService;
  
  @Autowired
  ChatRoomService chatRoomService;
  
  @Autowired
  ChatService chatService;
  
  
  @RequestMapping(value={"/seller", "/seller/home"}, method = RequestMethod.GET)
  public String sellerHome(){
    return "jsp/seller/home";
  }
  
  @RequestMapping(value="/seller/viewcontacts", method = RequestMethod.GET)
  public String viewContact(Model m){
    Person seller = getSeller();
    List<PrivateGroup> privateGroupList = connectionService.getPrivateGroupsByGroupOnwer(seller);
    m.addAttribute("privateGroups", privateGroupList);
    PrivateGroup privateGroup = new PrivateGroup();
    m.addAttribute("privateGroupForm", privateGroup);
    
    List<Connection> allConnections = connectionService.getAllSellerConnectionsUsingPrivateGroup(seller);
    m.addAttribute("connectionList", allConnections);
    m.addAttribute("privateGroupList", privateGroupList);
    return "jsp/seller/viewcontacts";
  }
  
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
    privateGroupDTO.setId(privateGroup.getId());
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
    return "redirect:/seller/viewgroup/"+oldGroupId;
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
    //List<Person> allConnections = connectionService.getAllSellersConnections(seller);
    //m.addAttribute("connectionList", allConnections);
    List<Connection> allConnections = connectionService.getAllSellerConnectionsUsingPrivateGroup(seller);
    
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
  
  
 
  /*
  @RequestMapping(value="/seller/sendinvitation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public String sendInvitation(@RequestBody SendSmsRequestAjaxDTO request){
    String response = SmsSender.sendSms(request.getMobileNo(),"Hello Chirag"); 
    return response;
  }
 */
  
  
  @RequestMapping(value="/seller/addconnection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public SellerAddConnectionResponseAjaxDTO addConnection(@RequestBody SellerAddConnectionRequestAjaxDTO request){
    Map<String, String> errors;
    SellerAddConnectionResponseAjaxDTO ajaxReply = new SellerAddConnectionResponseAjaxDTO();
    Person seller = getSeller();
    Person toPerson = partyManagementService.getPersonFromPhoneNumberUsername(request.getUserId());
    
    if(toPerson == null){
      SmsSender.sendSms(request.getUserId(),"Hello");   
      return ajaxReply;
    }

    
 // below code for add members into chatroom   
    List<Party> members = new ArrayList<Party>();
    members.add(seller);
    members.add(toPerson);
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.setMembers(members);
    chatRoomService.saveChatRoom(chatRoom);
 // Above  code for add members into chatroom
    
    
    errors = sellerValidator.validateAddConnection(seller, toPerson);
    if(errors.size()>0){
      ajaxReply.setErrors(errors);
      return ajaxReply;
    }
    
    /**
     * change other functions also to use generic createconnection
     */
    connectionService.createConnection(seller, toPerson, ConnectionType.SELLER_BUYER);
    PrivateGroup privateGroup = null;
    if(request.getGroupId()!=-1){
      privateGroup = partyManagementService.getPrivateGroup(request.getGroupId());
      connectionService.createConnection(privateGroup, toPerson, ConnectionType.GROUP_MEMBERS);
    }
    ajaxReply.addDetails(toPerson, privateGroup);
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
    
   // ChatRoom chatroom = chatRoomService.getChatRoomByMembers(seller.getId(), toPerson.getId());
   // chatService.deleteAllChatByChatRoomId(chatroom.getId());
    //chatRoomService.deleteChatRoom(chatroom.getId());
    
    
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
    PrivateGroup newPrivateGroup = null;
    if(request.getGroupId()!=-1){
      newPrivateGroup = partyManagementService.getPrivateGroup(request.getGroupId());
      connectionService.createConnection(newPrivateGroup, toPerson, ConnectionType.GROUP_MEMBERS);
    }
    ajaxReply.addDetails(toPerson, newPrivateGroup);
    return ajaxReply;
  }
  
  @RequestMapping(value="/seller/uploadproduct/category/{categoryId}", method=RequestMethod.GET)
  public String viewCategoryForUpload(Model m, @PathVariable Long categoryId){
    CategoryTree categoryTree = null;
    Person seller = getSeller();
    if(categoryId==null || categoryId==-1){
      categoryTree = seller.getCategoryRoot();
    }
    else{
      categoryTree = categoryService.getCategory(categoryId);
    }
    Boolean isLeaf = categoryTree.getIsLeaf();
    String parentCategoryName = categoryTree.getCategoryName();
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    if(isLeaf){
      //PropertyMetadata propertyMetadata = categoryService.getPropertyMetadata(seller, depth, categoryId);
      PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(categoryTree.getId());
      m.addAttribute("propertyMetadata", propertyMetadata);
//      ProductDetailDTO pdd = new ProductDetailDTO();
//      pdd.initialize(propertyMetadata);
      m.addAttribute("newItem", true);
      m.addAttribute("uploadForm", new ProductDetailDTO());
      m.addAttribute("categoryId", categoryTree.getId());
      m.addAttribute("parentCategoryName", parentCategoryName);
      
//      List<Connection> allConnections = connectionService.getAllSellerConnectionsUsingPrivateGroup(seller);
//      m.addAttribute("connections", allConnections);
      
      List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
      m.addAttribute("privateGroups", privateGroups);
      

      return "jsp/seller/viewuploadproduct";
    }
    else{
      //List<CategoryTree> categories = categoryService.getCategories(seller, depth, categoryId);
      List<CategoryTree> categories = categoryService.getCategories(categoryTree.getId());
      m.addAttribute("categoryList", categories);
      m.addAttribute("parentCategoryName", parentCategoryName);
      //m.addAttribute("depth", depth+1);
      return "jsp/seller/viewuploadcategory";
    }
  }
  
  @RequestMapping(value="/seller/uploadproduct/category/{categoryId}", method=RequestMethod.POST)
  public String saveProductUpload(@PathVariable Long categoryId, @ModelAttribute("uploadForm") ProductDetailDTO uploadForm){
    Map<Long, PropertyField> propertyFieldMap = propertyService.getPropertyFieldByCategoryIdMappedByPropertyFieldId(categoryId);
    Person seller = getSeller();
    Item item = new Item();
    item.setItemCategory(categoryService.getCategory(categoryId));
    item.setOwner(seller);
    item = itemService.saveItem(item);
    
    List<PropertyValue> propertyValues = propertyService.populatePropertyValues(propertyFieldMap, uploadForm.getFieldIds(), uploadForm.getValues(), item);
    item.setPropertyValues(propertyValues);
    
    Map<Long, ImageModel> metaImageModels = propertyService.getImageModelMetaByCategoryIdMappedByImageModelId(categoryId);
    List<ImageModel> valueImageModels = propertyService.populateImageModels(metaImageModels, uploadForm.getByteImagesFromBase64InOrder(), uploadForm.getImagesMetaId(), item);
    item.setImageModels(valueImageModels);
    
    List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
    Map<Long, PrivateGroup> privateGroupMap = connectionService.convertToMap(privateGroups);
    itemService.populateItemWithSharedPrivateGroups(item, privateGroupMap, uploadForm.getShare());
    
    itemService.saveItem(item);
    return "redirect:/seller/uploadproduct/category/"+categoryId+"/item/"+item.getId();
  }
  
  @RequestMapping(value="/seller/uploadproduct/category/{categoryId}/item/{itemId}", method=RequestMethod.GET)
  public String viewProductUpload(@PathVariable Long categoryId, @PathVariable Long itemId, Model m){
    Person seller = getSeller();
    PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(categoryId);
    m.addAttribute("propertyMetadata", propertyMetadata);
    
    Item item = itemService.getItemByItemIdAndOwner(itemId, seller);
    Map<Long, PropertyValue> propertyValueMap = propertyService.getPropertyValuesMappedByPropertyField(item.getPropertyValues());
    m.addAttribute("propertyValueMap", propertyValueMap);
    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
    
    Map<Long, ImageModel> valueImageModelMap = propertyService.getImageModelValuesMappedByImageModelMeta(item.getImageModels());
    m.addAttribute("valueImageModelMap", valueImageModelMap);
    
    List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
    m.addAttribute("privateGroups", privateGroups);
    
    List<PrivateGroup> sharedPrivateGroups = itemService.getSharedPrivateGroups(item);
    Map<Long,PrivateGroup> sharedPrivateGroupsMap = connectionService.convertToMap(sharedPrivateGroups);
    m.addAttribute("sharedPrivateGroupMap", sharedPrivateGroupsMap);
    
    m.addAttribute("itemId", itemId);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("uploadForm", productDetailDTO);
    m.addAttribute("categoryId", categoryId);
    m.addAttribute("parentCategoryName", categoryService.getCategory(categoryId).getCategoryName());
    return "jsp/seller/viewuploadproduct";
  }
  
  @RequestMapping(value="seller/uploadproduct/category/{categoryId}/item/{itemId}", method=RequestMethod.POST)
  public String editProductUpload(@PathVariable Long categoryId, @ModelAttribute("itemId") Long itemId, @ModelAttribute("uploadForm") ProductDetailDTO uploadForm){
    Person seller = getSeller();    
    Item item = itemService.getItemByItemIdAndOwner(itemId, seller);
    Map<Long, PropertyValue> propertyValueMapOld = propertyService.getPropertyValuesMappedByPropertyValue(item.getPropertyValues());
    List<PropertyValue> propertyValuesNew = propertyService.updatePropertyValues(propertyValueMapOld, uploadForm.getValueIds(), uploadForm.getValues());
    item.setPropertyValues(propertyValuesNew);
    Map<Long, ImageModel> metaImageModels = propertyService.getImageModelMetaByCategoryIdMappedByImageModelId(categoryId);
    item = propertyService.updateImageModelValues(metaImageModels, uploadForm.getByteImagesFromBase64InOrder(), uploadForm.getImagesMetaId(), uploadForm.getImagesValueId(), item);
    
    List<PrivateGroup> privateGroups = connectionService.getPrivateGroupsByGroupOnwer(seller);
    Map<Long, PrivateGroup> privateGroupMap = connectionService.convertToMap(privateGroups);
    itemService.populateItemWithSharedPrivateGroups(item, privateGroupMap, uploadForm.getShare());
    
    itemService.saveItem(item);
    return "redirect:/seller/uploadproduct/category/"+categoryId+"/item/"+itemId;
  }
  
  @RequestMapping(value="/seller/viewcategory/category/{categoryId}", method=RequestMethod.GET)
  public String viewCategory(Model m, @PathVariable Long categoryId){
    CategoryTree categoryTree = null;
    if(categoryId==null || categoryId==-1){
      Person seller = getSeller();
      categoryTree = seller.getCategoryRoot();
    }
    else{
      categoryTree = categoryService.getCategory(categoryId);
    }
    Boolean isLeaf = categoryTree.getIsLeaf();
    String parentCategoryName = categoryTree.getCategoryName();
    if(isLeaf){
      //PropertyMetadata propertyMetadata = categoryService.getPropertyMetadata(seller, depth, categoryId);
      
      return "redirect:/seller/viewproduct/category/"+categoryTree.getId();
    }
    else{
      //List<CategoryTree> categories = categoryService.getCategories(seller, depth, categoryId);
      List<CategoryTree> categories = categoryService.getCategories(categoryTree.getId());
      m.addAttribute("rootDir", propertyService.getImageDir());
      m.addAttribute("sizeDir", "360");
      m.addAttribute("imageExtn", "jpg");
      m.addAttribute("categoryList", categories);
      m.addAttribute("parentCategoryName", parentCategoryName);
      //m.addAttribute("depth", depth+1);
      return "jsp/seller/viewcategory";
    }
  }
  
  @RequestMapping(value="/seller/viewproduct/category/{categoryId}", method=RequestMethod.GET)
  public String viewProduct(Model m, @PathVariable Long categoryId){
    Person seller = getSeller();
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    m.addAttribute("parentCategoryName", categoryTree.getCategoryName());
    //PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(categoryId);
    //m.addAttribute("propertyMetadata", propertyMetadata);
    List<Item> items = itemService.getItemsByCategoryIdAndOwner(categoryId, seller.getId()); 
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("items", items);
    return "jsp/seller/viewproduct";
  }
  
  @RequestMapping(value="/seller/viewproduct/item/{itemId}", method=RequestMethod.GET)
  public String viewProduct(@PathVariable Long itemId, Model m){
    Person seller =  getSeller();
    Item item = itemService.getItemByItemIdAndOwner(itemId, seller);
    
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
    
    return "jsp/seller/viewitem";
  }
  
  @RequestMapping(value="/seller/viewfullimage/{id}/extn/{extn}")
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
  
  public Person getSeller(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
  
}