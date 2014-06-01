package com.bizbuzz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bizbuzz.model.ContactUs;
import com.bizbuzz.model.Order;
import com.bizbuzz.model.UserRegistration;
import com.bizbuzz.service.ContactUsService;
import com.bizbuzz.service.ShipperService;
import com.bizbuzz.service.UserRegistrationService;

@Controller
public class HomeController {
  
  @Autowired
  ContactUsService contactUsService;
  @Autowired
  ShipperService shipperService;
  @Autowired
  UserRegistrationService userRegistrationService;

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String home(Model m) {
    shipperService.getBookTruckFromHome(m);
    return "home";
  }
  
  @RequestMapping(value = { "home_success" }, method = RequestMethod.POST)
  public String homeSuccess(@ModelAttribute("bookingdata") Order order,@ModelAttribute("userregistration") UserRegistration userRegistration, ModelMap modelMap) {
    shipperService.saveBookTruckForm(order);
    ModelAndView mv = new ModelAndView();
    modelMap.addAttribute("bookingdata", order);
    mv.addObject("order",order);
    return "register-bookTruck";
  }

  @RequestMapping(value = { "/aboutus" }, method = RequestMethod.GET)
  public String aboutUs() {
    return "aboutus";
  }

  @RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
  public String contactUs(Model m) {
    contactUsService.getContactUsFormModelDetails(m);
    return "contactus";
  }
  
  @RequestMapping(value = { "contactus_success" }, method = RequestMethod.POST)
  public String saveContactUs(@ModelAttribute("contactus") ContactUs contactus,ModelMap modelMap) {
    contactUsService.save(contactus); 
    modelMap.addAttribute("successmessage"," Thank you for writing us. We will getback to you soon.");
    return "contactus";
  }

  @RequestMapping(value = { "/services" }, method = RequestMethod.GET)
  public String services() {
    return "services";
  }
  
  @RequestMapping(value = { "/track" }, method = RequestMethod.GET)
  public String tracking(@RequestParam("trackid")String trackId,ModelMap model){
    
    if(!trackId.equals("12"))
    {
      model.addAttribute("errormessage", "Invalid Tracking ID");
      return "forward:/home";
    }
    return "register";
  }

  @RequestMapping(value = { "/register-bookTruck" }, method = RequestMethod.GET)
  public String registerbookTruck(Model m) {
    shipperService.getBookTruckFromHome(m);
    userRegistrationService.getRegistrationDetailsFromModel(m);
    return "register-bookTruck";
  }
  
  
 
  
}
