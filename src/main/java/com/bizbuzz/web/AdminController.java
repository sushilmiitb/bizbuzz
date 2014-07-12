package com.bizbuzz.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizbuzz.dto.AdminAddCategoryRequestAjaxDTO;
import com.bizbuzz.dto.AdminAddCategoryResponseAjaxDTO;
import com.bizbuzz.dto.AdminSavePropertyMetaDataRequestAjaxDTO;
import com.bizbuzz.dto.GeneralAjaxRequestDTO;
import com.bizbuzz.dto.SellerAddPrivateGroupResponseAjaxDTO;
import com.bizbuzz.dto.GeneralAjaxResponseDTO;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.service.CategoryService;

@Controller
public class AdminController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  CategoryService categoryService;
 
  
  @RequestMapping(value="/admin/viewcategory/{categoryId}", method = RequestMethod.GET)
  public String viewACategory(@PathVariable Long categoryId, Model m){
    CategoryTree category = categoryService.getCategory(categoryId);
    List<CategoryTree> categoryList = categoryService.getCategories(categoryId);
    m.addAttribute("categoryList", categoryList);
    m.addAttribute("categoryId", categoryId);
    m.addAttribute("parentCategoryName", category.getCategoryName());
    return "jsp/admin/viewcategory";
  }
  
  @RequestMapping(value="/admin/viewcategory", method = RequestMethod.GET)
  public String viewAllCategories(Model m){
    CategoryTree category = categoryService.getCategory(1L);
    List<CategoryTree> categoryList = categoryService.getCategories(1L);
    m.addAttribute("categoryList", categoryList);
    m.addAttribute("categoryId", 1);
    m.addAttribute("parentCategoryName", category.getCategoryName());
    return "jsp/admin/viewcategory";
  }
  
  @RequestMapping(value="/admin/addcategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public AdminAddCategoryResponseAjaxDTO addCategory(@RequestBody AdminAddCategoryRequestAjaxDTO request){
    CategoryTree parentCategory = categoryService.getCategory(request.getParentId());
    CategoryTree category = categoryService.saveCategory(parentCategory, request.getCategoryName(), request.getIsLeaf());
    AdminAddCategoryResponseAjaxDTO response = new AdminAddCategoryResponseAjaxDTO(category.getCategoryName(), category.getId());
    return response;
  }
  
  @RequestMapping(value="/admin/category/edit/{categoryId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public AdminAddCategoryResponseAjaxDTO editCategory(@RequestBody AdminAddCategoryRequestAjaxDTO request, @PathVariable Long categoryId){
    CategoryTree category = categoryService.updateCategory(categoryId, request.getCategoryName(), request.getIsLeaf());
    AdminAddCategoryResponseAjaxDTO response = new AdminAddCategoryResponseAjaxDTO(category.getCategoryName(), category.getId());
    return response;
  }

  @RequestMapping(value="/admin/category/delete/{categoryId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public GeneralAjaxResponseDTO deleteCategory(@PathVariable Long categoryId){
    categoryService.deleteCategory(categoryId);
    GeneralAjaxResponseDTO response = new GeneralAjaxResponseDTO();
    response.setMessage("success");
    return response;
  }
  
  @RequestMapping(value="/admin/viewproperty/{categoryId}", method = RequestMethod.GET)
  public String viewPropertyMetadata(@PathVariable Long categoryId, Model m){
    List<PropertyMetadata> propertyMetadatas = categoryService.getPropertyMetadatas(categoryId);
    m.addAttribute("categoryId", categoryId);
    m.addAttribute("propertyMetadatas", propertyMetadatas);
    m.addAttribute("newPropertyMetadata", new PropertyMetadata());
    return "jsp/admin/viewproperty";
  }
  
  @RequestMapping(value="/admin/property/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public GeneralAjaxResponseDTO savePropertyMetadata(@RequestBody AdminSavePropertyMetaDataRequestAjaxDTO request){
    CategoryTree category = categoryService.getCategory(request.getCategoryId());
    categoryService.savePropertyMetadatas(request.getNewPropertyMetadata(), category);
    categoryService.updatePropertyMetadatas(request.getUpdatePropertyMetadata());
    GeneralAjaxResponseDTO response = new GeneralAjaxResponseDTO();
    response.setMessage("success");
    return response;
  }
  
}
