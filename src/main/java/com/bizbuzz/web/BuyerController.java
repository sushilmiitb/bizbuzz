package com.bizbuzz.web;

import java.util.List;

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
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;
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
      return "jsp/seller/viewcategory";
    }
  }
  
  @RequestMapping(value="/buyer/viewproduct/category/{categoryId}", method=RequestMethod.GET)
  public String viewProduct(Model m, @PathVariable Long categoryId){
    Person buyer = getBuyer();
    CategoryTree categoryTree = categoryService.getCategory(categoryId);
    m.addAttribute("parentCategoryName", categoryTree.getCategoryName());
    PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(categoryId);
    m.addAttribute("propertyMetadata", propertyMetadata);
    
    List<Item> items = itemService.getItemsOfAllSellersByCategoryAndBuyer(categoryTree, buyer);
    m.addAttribute("rootDir", propertyService.getImageDir());
    m.addAttribute("sizeDir", "360");
    m.addAttribute("imageExtn", "jpg");
    m.addAttribute("items", items);
    return "jsp/seller/viewproduct";
  }
  
  public Person getBuyer(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
}
