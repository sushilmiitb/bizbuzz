package com.bizbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bizbuzz.dto.CountryCodeDTO;
import com.bizbuzz.dto.RegistrationPersonRegistrationFormDTO;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.SecurityGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.CompanyRepository;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.repository.PartyRepository;
import com.bizbuzz.repository.PersonRepository;
import com.bizbuzz.repository.PhoneNumberRepository;
import com.bizbuzz.repository.PrivateGroupRepository;
import com.bizbuzz.repository.SecurityGroupRepository;
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
  @Autowired
  SecurityGroupRepository securityGroupRepository;
  @Autowired
  PrivateGroupRepository privateGroupRepository;
  @Autowired
  PartyRepository partyRepository;
  
  public void savePhoneNumber(PhoneNumber phoneNumber){
    if(phoneNumber!= null){
      phoneNumberRepository.save(phoneNumber);
    }
  }
  
  public void saveUserLogin(UserLogin userLogin, String securityGroupName){
    userLogin.addSecurityGroup(securityGroupRepository.findByName(securityGroupName));
    userLoginRepository.save(userLogin);
  }
  
  public void savePerson(Person person){
    personRepository.save(person);
  }
  
  public void saveCompany(Company company){
    if(company!=null){
      companyRepository.save(company);
    }
  }
  
  public List<String> getListOfPersonRole(){
    List<String> personRoleList = HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppConstants.xml"), "personrole");
    return personRoleList;
  }
  
  public List<String> getListOfCompanyRole(){
    List<String> companyRoleList = HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppConstants.xml"), "companyrole");
    return companyRoleList;
  }
  
  public void savePrivateGroup(PrivateGroup privateGroup){
    privateGroupRepository.save(privateGroup);
  }
  
  public Person getPersonFromUsername(String username){
    UserLogin userLogin = userLoginRepository.findById(username);
    return userLogin.getPerson();
  }
  
  public void updatePrivateGroup(PrivateGroup oldPrivateGroup, PrivateGroup updatedPrivateGroup){
    oldPrivateGroup.setPrivateGroupName(updatedPrivateGroup.getPrivateGroupName());
    privateGroupRepository.save(oldPrivateGroup);
  }
  
  public void deletePrivateGroup(PrivateGroup privateGroup){
    privateGroupRepository.delete(privateGroup.getId());
  }
  
  public Person getPersonFromPhoneNumberUsername(String phonenumber){
    UserLogin userLogin = userLoginRepository.findById(phonenumber);
    if(userLogin==null){
      return null;
    }else{
      return userLogin.getPerson();
    }
  }
  
  public Party getParty(Long id){
    return partyRepository.getOne(id);
  }
  
  public PrivateGroup getPrivateGroup(Long id){
    return privateGroupRepository.findOne(id);
  }
  
  public void deletePerson(Person person){
    personRepository.delete(person);
  }
  
  public Person getPerson(Long id){
    return personRepository.findOne(id);
  }

/*  @Override
  public List<CountryCodeDTO> getListOfCountryCodes() {
    List<CountryCodeDTO> countryCodeList = HelperFunctions.retrieveResourcesCountryCodes(getClass().getResourceAsStream("/application/CountryCodes.xml"), "countryCode");
    return countryCodeList;
  }
*/
}
