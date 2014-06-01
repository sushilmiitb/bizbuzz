package com.bizbuzz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bizbuzz.model.UserRegistration;
import com.bizbuzz.service.UserRegistrationService;

@Controller
public class AdminController {
  
  @Autowired
  UserRegistrationService userRegistrationService;
  
  List<UserRegistration> registeredUsers;
  
  @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
  
  public ModelAndView  admin()
  {
    registeredUsers = userRegistrationService.getAllRegistrationDetails();
    for(UserRegistration userRegistration : registeredUsers)
    {
      System.out.println(userRegistration.getFirstname()+"\n");
    }
    return new ModelAndView("admin_registeredusers", "userdetails", registeredUsers);
  }
  
}
