package com.bizbuzz.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizbuzz.dto.AdminAddCategoryRequestAjaxDTO;
import com.bizbuzz.dto.AdminAddCategoryResponseAjaxDTO;
import com.bizbuzz.dto.AdminSavePropertyMetaDataRequestAjaxDTO;
import com.bizbuzz.dto.GeneralAjaxRequestDTO;
import com.bizbuzz.dto.SellerAddPrivateGroupAjaxDTO;
import com.bizbuzz.dto.GeneralAjaxResponseDTO;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.PropertyService;

@Controller
public class AdminController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  CategoryService categoryService;
  @Autowired
  PropertyService propertyService;
 
  
  @RequestMapping(value="/admin/viewcategory/{categoryId}", method = RequestMethod.GET)
  public String viewACategory(@PathVariable Long categoryId, Model m){
    CategoryTree category = categoryService.getCategory(categoryId);
    List<CategoryTree> categoryList = categoryService.getAdminCategories(categoryId);
    m.addAttribute("categoryList", categoryList);
    m.addAttribute("categoryId", categoryId);
    m.addAttribute("parentCategoryName", category.getCategoryName());
    return "jsp/admin/viewcategory";
  }
  
  @RequestMapping(value={"/admin/", "/admin/home/", "/admin/viewcategory"}, method = RequestMethod.GET)
  public String viewAllCategories(Model m){
    CategoryTree category = categoryService.getCategory(1L);
    List<CategoryTree> categoryList = categoryService.getAdminCategories(1L);
    m.addAttribute("categoryList", categoryList);
    m.addAttribute("categoryId", 1);
    m.addAttribute("parentCategoryName", category.getCategoryName());
    return "jsp/admin/viewcategory";
  }
  
  @RequestMapping(value="/admin/addcategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public AdminAddCategoryResponseAjaxDTO addCategory(@RequestBody AdminAddCategoryRequestAjaxDTO request){
    CategoryTree parentCategory = categoryService.getCategory(request.getParentId());
    CategoryTree category = categoryService.saveAdminCategory(parentCategory, request.getCategoryName(), request.getIsLeaf(), request.getHasProduct());
    AdminAddCategoryResponseAjaxDTO response = new AdminAddCategoryResponseAjaxDTO(category.getCategoryName(), category.getId());
    return response;
  }
  
  @RequestMapping(value="/admin/category/edit/{categoryId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public AdminAddCategoryResponseAjaxDTO editCategory(@RequestBody AdminAddCategoryRequestAjaxDTO request, @PathVariable Long categoryId){
    CategoryTree category = categoryService.updateAdminCategory(categoryId, request.getCategoryName(), request.getIsLeaf(), request.getHasProduct());
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
  
  @RequestMapping(value="/admin/viewproperty/category/{categoryId}", method = RequestMethod.GET)
  public String viewPropertyMetadata(@PathVariable Long categoryId, Model m){
    //List<PropertyMetadata> propertyMetadatas = categoryService.getPropertyMetadatas(categoryId);
    PropertyMetadata propertyMetadata = propertyService.getPropertyMetadata(categoryId);
    if(propertyMetadata==null){
      propertyMetadata = new PropertyMetadata();
    }
    m.addAttribute("categoryId", categoryId);
    m.addAttribute("propertyMetadata", propertyMetadata);
    return "jsp/admin/viewproperty";
  }
  
  @RequestMapping(value="/admin/property/save/category/{categoryId}", method = RequestMethod.POST)
  public String savePropertyMetadata(Model m, @PathVariable("categoryId")Long categoryId, @ModelAttribute("propertyMetadata") PropertyMetadata propertyMetadata, @RequestParam("propertyMetadataId") Long propertyMetadataId){
    //if(propertyMetadataId != null){
      propertyService.savePropertyMetadata(propertyMetadata, categoryId);
    //}
    //propertyMetadata.setId(propertyMetadataId);
    //else{
    //  propertyService.savePropertyMetadata(propertyMetadata, categoryId);
    //}
    m.addAttribute("propertyMetadata", propertyMetadata);
    m.addAttribute("categoryId", categoryId);
    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
  }
  
  @RequestMapping(value="/admin/property/delete/category/{categoryId}/field/{fieldId}", method = RequestMethod.GET)
  public String deleteFieldProperty(@PathVariable("categoryId")Long categoryId, @PathVariable("fieldId") Long fieldId){
    propertyService.deletePropertyField(fieldId);
    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
  }
  
  @RequestMapping(value="/admin/property/delete/category/{categoryId}/group/{groupId}", method = RequestMethod.GET)
  public String deleteGroupProperty(@PathVariable("categoryId")Long categoryId, @PathVariable("groupId") Long groupId){
    propertyService.deletePropertyGroup(groupId);
    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
  }
  
  @RequestMapping(value="/admin/property/delete/category/{categoryId}/subgroup/{subgroupId}", method = RequestMethod.GET)
  public String deleteSubGroupProperty(@PathVariable("categoryId")Long categoryId, @PathVariable("subgroupId") Long subgroupId){
    propertyService.deletePropertySubGroup(subgroupId);
    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
  }
  
  @RequestMapping(value="/admin/property/delete/category/{categoryId}/image/{imageId}", method = RequestMethod.GET)
  public String deleteImageProperty(@PathVariable("categoryId")Long categoryId, @PathVariable("imageId") Long imageId){
    propertyService.deleteImageModel(imageId);
    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
  }
//  
//  @RequestMapping(value="/admin/property/link/category/${categoryId}", method = RequestMethod.POST)
//  public String linkExistingPropertyMetadata(Model m, @PathVariable("categoryId")Long categoryId, @RequestParam("propertyMetadataId")Long propertyId){
//    PropertyMetadata propertyMetadata =categoryService.saveExistingPropertyMetadata(propertyId, categoryId);
//    m.addAttribute("propertyMetadata", propertyMetadata);
//    m.addAttribute("categoryId", categoryId);
//    return "redirect:/admin/viewproperty/category/"+categoryId.toString();
//  }
}
