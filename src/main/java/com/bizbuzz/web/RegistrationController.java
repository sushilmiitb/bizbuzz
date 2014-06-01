package com.bizbuzz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizbuzz.model.UserRegistration;
import com.bizbuzz.service.UserRegistrationService;

@Controller
public class RegistrationController {

  @Autowired
  UserRegistrationService userRegistrationService;

  @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
  public String registration(Model m) {
    userRegistrationService.getRegistrationDetailsFromModel(m);
    return "register";
  }

  @RequestMapping(value = { "register_success" },method = RequestMethod.POST)
  public String saveRegistrationData(@ModelAttribute("userregistration") UserRegistration userregistration, ModelMap modelMap) {
    userRegistrationService.saveRegistrationDetails(userregistration);
    modelMap.addAttribute("successmessage","Thank you for registering with us. Our cutsomer representative will call you to verify the registration");
    return "register";
  }
}
