package com.bizbuzz.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ItemService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PropertyService;

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
  
  @RequestMapping(value={"/buyer", "/buyer/home"}, method = RequestMethod.GET)
  public String buyerHome(){
    return "jsp/buyer/home";
  }
  
  @RequestMapping(value="/buyer/viewcategory/category/{categoryId}", method=RequestMethod.GET)
  public String viewCategory(Model m, @PathVariable Long categoryId){
    CategoryTree categoryTree = null;
    if(categoryId==null || categoryId==-1){
      Person buyer = getBuyer();
      categoryTree = buyer.getCategoryRoot();
    }
    else{
      categoryTree = categoryService.getCategory(categoryId);
    }
    Boolean isLeaf = categoryTree.getIsLeaf();
    String parentCategoryName = categoryTree.getCategoryName();
    if(isLeaf){
      //PropertyMetadata propertyMetadata = categoryService.getPropertyMetadata(seller, depth, categoryId);
      
      return "redirect:/buyer/viewproduct/category/"+categoryTree.getId();
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
      return "jsp/buyer/viewcategory";
    }
  }
  
  @RequestMapping(value="/buyer/viewseller/category/{categoryId}", method=RequestMethod.GET)
  public String viewSellerOfACategory(Model m, @PathVariable Long categoryId){
    Person buyer = getBuyer();
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    List<Person> sellers = itemService.getSellersByBuyerIdOrderByLatestItemUpload(buyer, categoryTree);
    List<Company> companies = itemService.getCompaniesOfSellersByBuyerIdOrderByLatestItemUpload(buyer, categoryTree);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("category", categoryTree);
    m.addAttribute("sellers", sellers);
    m.addAttribute("companies", companies);
    return "jsp/buyer/viewseller";
  }
  
  @RequestMapping(value="/buyer/viewproduct/category/{categoryId}/seller/{sellerId}", method=RequestMethod.GET)
  public String viewProduct(Model m, @PathVariable Long categoryId, @PathVariable Long sellerId){
    Person buyer = getBuyer();
    Person seller = partyManagementService.getPerson(sellerId);
    m.addAttribute("seller", seller);
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    m.addAttribute("parentCategory", categoryTree);
    
    List<Item> items = itemService.getItemsByCategoryIdAndOwnerAndBuyer(categoryId, sellerId, buyer.getId());
    
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("items", items);
    return "jsp/buyer/viewproduct";
  }
  
  @RequestMapping(value="/buyer/viewproduct/item/{itemId}/seller/{sellerId}", method=RequestMethod.GET)
  public String viewProduct(@PathVariable Long itemId, @PathVariable Long sellerId, Model m){
    Person buyer =  getBuyer();
    
    Person seller = partyManagementService.getPerson(sellerId);
    m.addAttribute("seller", seller);
    
    Item item = itemService.getItemByItemIdAndOwnerAndBuyer(itemId, sellerId, buyer.getId());
    
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
