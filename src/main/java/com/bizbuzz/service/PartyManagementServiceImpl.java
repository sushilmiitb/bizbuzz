package com.bizbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bizbuzz.dto.PersonRegistrationDTO;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.CompanyRepository;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.repository.PersonRepository;
import com.bizbuzz.repository.PhoneNumberRepository;
import com.bizbuzz.repository.UserLoginRepository;
import com.bizbuzz.utils.HelperFunctions;

@Service
public class PartyManagementServiceImpl implements PartyManagementService {
  @Autowired
  PhoneNumberRepository phoneNumberRepository;
  @Autowired
  UserLoginRepository userLoginRepository;
  @Autowired
  CompanyRepository companyRepository;
  @Autowired
  PersonRepository personRepository;
  @Autowired
  ConnectionRepository connectionRepository;
  
  public void getPersonRegistrationForm(Model m){
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
    //m.addAttribute("personRoleList", this.getListOfPersonRole());
    m.addAttribute("companyRoleList", this.getListOfCompanyRole());
  }
  
  public void savePersonRegistrationForm(Person person, Company company, PhoneNumber phoneNumber, UserLogin userLogin){
    phoneNumberRepository.save(phoneNumber);
    person.addPhoneNumber(phoneNumber);
    userLoginRepository.save(userLogin);
    companyRepository.save(company);
    person.setUserId(userLogin);
    personRepository.save(person);
    
    Connection connection = new Connection(company, person, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    company.addToParty(connection);
    person.addFromParty(connection);
    personRepository.save(person);
    companyRepository.save(company);
  }
  
  public void savePersonRegistrationForm(PersonRegistrationDTO personRegistration){
    Person person = personRegistration.getPerson();
    UserLogin userLogin = personRegistration.getUserLogin();
    Company company = personRegistration.getCompany();
    PhoneNumber phoneNumber = personRegistration.getPhoneNumber();
    
    phoneNumberRepository.save(phoneNumber);
    person.addPhoneNumber(phoneNumber);
    userLoginRepository.save(userLogin);
    companyRepository.save(company);
    person.setUserId(userLogin);
    personRepository.save(person);
    
    Connection connection = new Connection(company, person, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    company.addToParty(connection);
    person.addFromParty(connection);
    personRepository.save(person);
    companyRepository.save(company);
  }
  
  public List<String> getListOfPersonRole(){
    List<String> personRoleList = HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppConstants.xml"), "personrole");
    return personRoleList;
  }
  
  public List<String> getListOfCompanyRole(){
    List<String> companyRoleList = HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppConstants.xml"), "companyrole");
    return companyRoleList;
  }
  
  public void getAddBuyerFormForSeller(Model m){
    
  }
}
