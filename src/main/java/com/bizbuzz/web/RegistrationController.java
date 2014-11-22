package com.bizbuzz.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.RegisterDevice;
import com.bizbuzz.model.RegisterRequest;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.repository.RegisterRequestsRepository;
import com.bizbuzz.service.CategoryService;
import com.bizbuzz.service.ChatRoomService;
import com.bizbuzz.service.ChatroomMemberService;
import com.bizbuzz.service.ConnectionService;
import com.bizbuzz.service.PartyManagementService;
import com.bizbuzz.service.PartyManagementServiceImpl;
import com.bizbuzz.service.RegisterDeviceService;
import com.bizbuzz.utils.HelperFunctions;
import com.bizbuzz.utils.SmsSender;

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
  AuthenticationManager authenticationManager;
  
//  @Autowired
//  private UserDetailsManager manager;
  
  @Autowired
  private UserDetailsService manager;
  
  @Autowired
  RegisterRequestsRepository registerRequestsRepository;
  
  @Autowired
  ChatroomMemberService chatroomMemberService;
  
  @Autowired
  ChatRoomService chatRoomService;
  
  @Autowired
  RegisterDeviceService registerDeviceService;

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
    logger.info("register/personregistration");
    // m.addAttribute("countryCodeList", partyManagementService.getListOfCountryCodes());
    return "jsp/register/personregistration";
  }

  @RequestMapping(value="/register/personregistration", method = RequestMethod.POST)
  public String savePersonRegistrationForm(@ModelAttribute("personRegistration") @Validated RegistrationPersonRegistrationFormDTO personRegistration, BindingResult bindingResult, Model model, HttpServletRequest request){
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
    List<RegisterRequest> registerRequests = registerRequestsRepository.findByToPartyPhoneNumber(personRegistration.getUserLogin().getId());
    for(int i=0; registerRequests!=null && i<registerRequests.size(); i++){
      RegisterRequest registerRequest = registerRequests.get(i);
      if(registerRequest.getFromParty()!=null)
        connectionService.createConnection(registerRequest.getFromParty(), personRegistration.getPerson(), ConnectionType.SELLER_BUYER);
      else
        continue;
      if(registerRequest.getPrivateGroup()!=null)
        connectionService.createConnection(registerRequest.getPrivateGroup(), personRegistration.getPerson(), ConnectionType.GROUP_MEMBERS);
      ChatRoom chatroom = new ChatRoom();
      chatRoomService.saveChatRoom(chatroom);       
      Date chatroomCreationDate= new Date();
      chatroomMemberService.createChatroomMember(chatroom,registerRequest.getFromParty(),chatroomCreationDate);
      chatroomMemberService.createChatroomMember(chatroom, personRegistration.getPerson(),chatroomCreationDate);
    }
    /**
     * Auto Login after successfull registration
     */
    UserDetails userDetails = manager.loadUserByUsername (personRegistration.getUserLogin().getId());
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());

    // generate session if one doesn't exist
    request.getSession();

    token.setDetails(new WebAuthenticationDetails(request));
    
    try{
      Authentication authenticatedUser = authenticationManager.authenticate(token);
      SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }catch(Exception e){
      e.printStackTrace();
    }
    // Congratulate the person on registration in InstaTrade By Sending message
    SmsSender.sendSms(personRegistration.getUserLogin().getId(), "Dear " +personRegistration.getPerson().getFirstName() 
        +", Congratulations! You have successfully registered On InstaTrade." 
        +"  Now You can use the features of InstaTrade.");           
/*
    UserDetails userDetails = manager.loadUserByUsername (personRegistration.getUserLogin().getId());
    Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
    SecurityContextHolder.getContext().setAuthentication(auth);*/
    //return "jsp/register/registrationsuccess";
    return "redirect:/rolehome";
  }

  @RequestMapping(value="/login/{error}", method = RequestMethod.GET)
  public String errorInLogin(Model model ,@PathVariable final String error){
    model.addAttribute("error", error);
    // model.addAttribute("countryCodeList",partyManagementService.getListOfCountryCodes());
    return "jsp/register/login";
  }

  @RequestMapping(value="/login", method = RequestMethod.GET)
  public String login(Model m){
    return "jsp/register/login";
  }

  @RequestMapping(value="/rolehome", method = RequestMethod.GET)
  public String getRoleHome(HttpSession session){
    Person person = getPerson();
    if(person!=null){
      session.setAttribute("senderId", person.getId());
      session.setAttribute("userId", person.getUserId().getId());
      session.setAttribute("userName", person.getFirstName()+" "+person.getMiddleName()+" "+person.getLastName());
    }

    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    if(authorities==null ||authorities.size()==0){
      return "redirect:/home";
    }
    if(authorities.size()==1){
      for(GrantedAuthority authority: authorities){
        String authorityString = authority.getAuthority();
        if(authorityString.equals("ROLE_BUYER")){
          session.setAttribute("userRole", "buyer");
          return "redirect:/buyer/viewcategory/category/-1";
        }
        if(authorityString.equals("ROLE_SELLER")){
          session.setAttribute("userRole", "seller");
          session.setAttribute("senderId", person.getId());
          return "redirect:/seller/viewcategory/category/-1";
        }
        if(authorityString.equals("ROLE_ADMIN")){
          session.setAttribute("userRole", "admin");
          return "redirect:/admin/home/";
        }
      }
    }
    return "redirect:/home";
  }
  
  @RequestMapping(value="/register/deviceregistration", method = RequestMethod.POST)
  public String getRegistationIdFromAndroidDevice(@RequestParam Map<String, String> allRequestParams){
    String username =allRequestParams.get("username").trim();
    String deviceRegistrationId = allRequestParams.get("regId");
    Person person = partyManagementService.getPersonFromUsername("+" +username);
    
    RegisterDevice registerDevice = new RegisterDevice();
    registerDevice.setDeviceRegistrationId(deviceRegistrationId);
    registerDevice.setParty(person);
    registerDeviceService.saveRegisterDevice(registerDevice);
  
    return "";
  } 

  public Person getPerson(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = user.getUsername();
    return partyManagementService.getPersonFromUsername(username);
  }
}