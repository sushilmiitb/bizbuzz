package com.bizbuzz.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jws.WebParam.Mode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.bizbuzz.dto.CountryCodeDTO;
import com.bizbuzz.dto.RegistrationPersonRegistrationFormDTO;
import com.bizbuzz.form.validator.Phone;
import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PartyManagementServiceImpl;
import com.bizbuzz.utils.HelperFunctions;

@Controller
public class RegistrationController {
  private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

  @Autowired
  PartyManagementService partyManagementService;

  @Autowired
  ConnectionService connectionService;

  @Autowired
  CategoryService categoryService;

  @Autowired
  @Qualifier("personRegistrationFormValidator")
  private Validator validator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(validator);
  }

  @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
  public String home(){
    return "redirect:/login";
  }

  @RequestMapping(value="/register/personregistration", method = RequestMethod.GET)
  public String getPersonRegistrationForm(Model m){
    
    RegistrationPersonRegistrationFormDTO personRegistration = new RegistrationPersonRegistrationFormDTO();
    UserLogin userLogin = new UserLogin();
    personRegistration.setUserLogin(userLogin);
    Person person = new Person();
    personRegistration.setPerson(person);
    Company company = new Company();
    personRegistration.setCompany(company);
    PhoneNumber phoneNumber = new PhoneNumber();
    personRegistration.setPhoneNumber(phoneNumber);
    CountryCodeDTO countryCodeDTO = new CountryCodeDTO();
    personRegistration.setCountryCodeDTO(countryCodeDTO); 
    m.addAttribute("personRegistration", personRegistration);
    m.addAttribute("companyRoleList", partyManagementService.getListOfCompanyRole());
   // m.addAttribute("countryCodeList", partyManagementService.getListOfCountryCodes());
    return "jsp/register/personregistration";
  }

  @RequestMapping(value="/register/personregistration", method = RequestMethod.POST)
  public String savePersonRegistrationForm(@ModelAttribute("personRegistration") @Validated RegistrationPersonRegistrationFormDTO personRegistration, BindingResult bindingResult, Model model){
    if (bindingResult.hasErrors()) {
      logger.info("Form validation Error.");
      //model.addAttribute("personRoleList", partyManagementService.getListOfPersonRole());
      model.addAttribute("companyRoleList", partyManagementService.getListOfCompanyRole());
      return "jsp/register/personregistration";
    }
  //  String phoneNumberWithCountryCode = personRegistration.getCountryCodeDTO().getNumericCode()+personRegistration.getUserLogin().getId();
  //  personRegistration.getUserLogin().setId(phoneNumberWithCountryCode);
    
    partyManagementService.savePhoneNumber(personRegistration.getPhoneNumber());
    partyManagementService.saveUserLogin(personRegistration.getUserLogin(), personRegistration.getCompany().getCompanyRole().toLowerCase());
    personRegistration.getPerson().setUserId(personRegistration.getUserLogin());
    Person person = personRegistration.getPerson();
    Long categoryId = Long.parseLong(HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppConstants.xml"), "sarees").get(0));
    CategoryTree categoryRoot = categoryService.getCategory(categoryId);
    person.setCategoryRoot(categoryRoot);
    partyManagementService.savePerson(personRegistration.getPerson());

    partyManagementService.saveCompany(personRegistration.getCompany());
    connectionService.createConnection(personRegistration.getCompany(), personRegistration.getPerson(), ConnectionType.COMPANY_PERSON);

    if(personRegistration.getCompany().getCompanyRole().equals("Seller")){
      PrivateGroup privateGroup = new PrivateGroup();
      privateGroup.setPrivateGroupName("General");
      partyManagementService.savePrivateGroup(privateGroup);
      connectionService.createConnection(person, privateGroup, ConnectionType.GROUPOWNER_GROUP);
    }
    return "jsp/register/registrationsuccess";
  }

  @RequestMapping(value="/login/{error}", method = RequestMethod.GET)
  public String errorInLogin(Model model ,@PathVariable final String error){
    model.addAttribute("error", error);
   // model.addAttribute("countryCodeList",partyManagementService.getListOfCountryCodes());
    return "jsp/register/login";
  }

  @RequestMapping(value="/login", method = RequestMethod.GET)
  public String login(Model m){
    
   // m.addAttribute("countryCodeList", partyManagementService.getListOfCountryCodes());
    return "jsp/register/login";
  }

  @RequestMapping(value="/rolehome", method = RequestMethod.GET)
  public String getRoleHome(){
    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    if(authorities==null ||authorities.size()==0){
      return "redirect:/home";
    }
    if(authorities.size()==1){
      for(GrantedAuthority authority: authorities){
        String authorityString = authority.getAuthority();
        if(authorityString.equals("ROLE_BUYER")){
          return "redirect:/buyer/home";
        }
        if(authorityString.equals("ROLE_SELLER")){
          return "redirect:/seller/home";
        }
        if(authorityString.equals("ROLE_ADMIN")){
          return "redirect:/admin/home/";
        }
      }
    }
    return "redirect:/home";
  }
}