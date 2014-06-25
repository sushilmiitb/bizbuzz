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
import org.springframework.web.bind.annotation.PathVariable;
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
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PartyManagementServiceImpl;

@Controller
public class RegistrationController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
  
  @Autowired
  PartyManagementService partyManagementService;
  
  @Autowired
  ConnectionService connectionService;
  
  @Autowired
  @Qualifier("personRegistrationFormValidator")
  private Validator validator;
  
  @InitBinder
  private void initBinder(WebDataBinder binder) {
      binder.setValidator(validator);
  }
  
  @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
  public String home(){
    return "home";
  }
  
  @RequestMapping(value="/register/personregistration", method = RequestMethod.GET)
  public String getPersonRegistrationForm(Model m){
    PersonRegistrationDTO personRegistration = new PersonRegistrationDTO();
    UserLogin userLogin = new UserLogin();
    personRegistration.setUserLogin(userLogin);
    Person person = new Person();
    personRegistration.setPerson(person);
    Company company = new Company();
    personRegistration.setCompany(company);
    PhoneNumber phoneNumber = new PhoneNumber();
    personRegistration.setPhoneNumber(phoneNumber);
    m.addAttribute("personRegistration", personRegistration);
    m.addAttribute("companyRoleList", partyManagementService.getListOfCompanyRole());
    return "jsp/register/personregistration";
  }
  
  @RequestMapping(value="/register/personregistration", method = RequestMethod.POST)
  public String savePersonRegistrationForm(@ModelAttribute("personRegistration") @Validated PersonRegistrationDTO personRegistration, BindingResult bindingResult, Model model){
    if (bindingResult.hasErrors()) {
      logger.info("Form validation Error.");
      //model.addAttribute("personRoleList", partyManagementService.getListOfPersonRole());
      model.addAttribute("companyRoleList", partyManagementService.getListOfCompanyRole());
      return "jsp/register/personregistration";
    }
    partyManagementService.savePhoneNumber(personRegistration.getPhoneNumber());
    partyManagementService.saveUserLoginWithSecurityGroup(personRegistration.getUserLogin(), personRegistration.getCompany().getCompanyRole().toLowerCase());
    partyManagementService.savePersonWithUserName(personRegistration.getPerson(), personRegistration.getUserLogin());
    partyManagementService.saveCompany(personRegistration.getCompany());
    connectionService.createConnection(personRegistration.getPerson(), personRegistration.getCompany(), ConnectionType.COMPANY_PERSON);
    
    return "jsp/register/registrationsuccess";
  }
  
  @RequestMapping(value="/login/{error}", method = RequestMethod.GET)
  public String errorInLogin(Model model, @PathVariable final String error){
    model.addAttribute("error", error);
    return "jsp/register/login";
  }
  
  @RequestMapping(value="/login", method = RequestMethod.GET)
  public String login(){
    return "jsp/register/login";
  }
}