package com.bizbuzz.web.seller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizbuzz.web.RegistrationController;

@Controller
public class SellerController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @RequestMapping(value="/seller/addbuyer", method = RequestMethod.GET)
  public String getAddBuyerForm(Model m){
    
    return "seller/addbuyer";
  }
  
  @RequestMapping(value="/seller/addbuyer", method = RequestMethod.POST)
  public String saveBuyer(Model m){
    return "seller/addbuyer";
  }
  
  @RequestMapping(value="/seller/groupmanagementpage", method = RequestMethod.GET)
  public String getGroupManagementPage(Model m){
    return "seller/groupmanagementpage";
  }
  
  @RequestMapping(value="/seller/groupmanagementpage", method = RequestMethod.POST)
  public String saveGroup(Model m){
    return "seller/groupmanagementpage";
  }
}
