package com.bizbuzz.form.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ConnectionService;

@Component
public class SellerValidator {  
  
  @Autowired
  ConnectionService connectionService;
  
  @Autowired
  CategoryService categoryService;
  
  @Autowired
  MessageSource messageSource;

//  @Value("${sellervalidator.validateprivategroupsave.duplicatename}")
//  private String validateprivategroupsaveDuplicatename;
  
  public Map<String, String> validateAddPrivateGroup(PrivateGroup privateGroup, Person person){
    Map<String, String> errors = new HashMap<String, String>();
    //Checking duplication of groupname
    String groupName = privateGroup.getPrivateGroupName().toLowerCase();
    List<PrivateGroup> privateGroupList = connectionService.getPrivateGroupsByGroupOnwer(person);
    for(int i=0;i<privateGroupList.size();i++){
      if(groupName.equals(privateGroupList.get(i).getPrivateGroupName().toLowerCase())){
        //errors.add(validateprivategroupsaveDuplicatename);
        errors.put("duplicate_name", messageSource.getMessage("sellervalidator.validateaddprivategroup.duplicatename", null, "", null));
        break;
      }
    }
    return errors;
  }
  
  public Map<String, String> validateAddCategory(Long parentCategoryId, String categoryName, Person person){
    Map<String, String> errors = new HashMap<String, String>();
    //Checking duplication of categoryname
    
    List<CategoryTree> categoryTreeListByCustom = categoryService.getCustomCategories(parentCategoryId, person.getId());
    List<CategoryTree> categoryTreeListByAdmin = categoryService.getAdminCategories(parentCategoryId);
    categoryTreeListByCustom.addAll(categoryTreeListByAdmin);
    for(int i=0;i<categoryTreeListByCustom.size();i++){
      if(categoryName.toLowerCase().equals(categoryTreeListByCustom.get(i).getCategoryName().toLowerCase())){
        //errors.add(validateprivategroupsaveDuplicatename);
        errors.put("duplicate_name", messageSource.getMessage("sellervalidator.validateaddcategory.duplicatename", null, "", null));
        break;
      }
    }
    return errors;
  }
  
  public Map<String, String> validateAddConnection(Party fromParty, Party toParty){
    Map<String, String> errors = new HashMap<String, String>();
    //Checking presence of toParty
    if(toParty==null){
      errors.put("to_party_absent", messageSource.getMessage("sellervalidator.validateaddConnection.topartyabsent", null, "", null));
      return errors;
    }
    //Checking if connection already exist
    Connection connection = connectionService.getConnection(fromParty, toParty);
    if(connection!=null){
      errors.put("duplicate_connection", messageSource.getMessage("sellervalidator.validateaddConnection.duplicateconnection", null, "", null));
    }
    
    return errors;
  }
  
  public Map<String, String> validateEditConnectionChangeGroup(Party fromParty, Party toParty){
    Map<String, String> errors = new HashMap<String, String>();
    //Checking presence of toParty
    if(toParty==null){
      errors.put("to_party_absent", messageSource.getMessage("sellervalidator.validateaddConnection.topartyabsent", null, "", null));
      return errors;
    }
    //Checking if connection already exist
    Connection connection = connectionService.getConnection(fromParty, toParty);
    if(connection==null){
      errors.put("duplicate_connection", messageSource.getMessage("sellervalidator.validateeditconnectionchangegroup.connectionnotexist", null, "", null));
    }
    return errors;
  }
}
