package com.bizbuzz.web;

import javax.jws.WebParam.Mode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bizbuzz.dto.PersonRegistrationDTO;
import com.bizbuzz.form.validator.Phone;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PartyManagementServiceImpl;

@Controller
public class RegistrationController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  PartyManagementService partyManagementService;
  
  @Autowired
  @Qualifier("personRegistrationFormValidator")
  private Validator validator;
  
  @InitBinder
  private void initBinder(WebDataBinder binder) {
      binder.setValidator(validator);
  }
  
  @RequestMapping(value="/register/personregistration", method = RequestMethod.GET)
  public String getPersonRegistrationForm(Model m){
    partyManagementService.getPersonRegistrationForm(m);
    return "jsp/register/personregistration";
  }
  
  /*@RequestMapping(value="/personregistration", method = RequestMethod.POST)
  public String savePersonRegistrationForm(@ModelAttribute("person") Person person, BindingResult personResult,
      @ModelAttribute("company") Company company, BindingResult companyResult,
      @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, BindingResult phoneNumberResult,
      @ModelAttribute("userLogin") UserLogin userLogin, BindingResult userLoginResult){
    partyManagementService.savePersonRegistrationForm(person, company, phoneNumber, userLogin);
    return "personregistration";
  }*/
  
  @RequestMapping(value="/register/personregistration", method = RequestMethod.POST)
  public String savePersonRegistrationForm(@ModelAttribute("personRegistration") @Validated PersonRegistrationDTO personRegistration, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      logger.info("Form validation Error.");
      return "jsp/register/personregistration";
    }
    partyManagementService.savePersonRegistrationForm(personRegistration);
    return "jsp/register/personregistration";
  }
}