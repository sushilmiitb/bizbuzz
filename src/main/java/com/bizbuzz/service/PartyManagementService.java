package com.bizbuzz.service;

import java.util.List;

import org.springframework.ui.Model;

import com.bizbuzz.dto.CountryCodeDTO;
import com.bizbuzz.dto.RegistrationPersonRegistrationFormDTO;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.UserLogin;


public interface PartyManagementService {
  public void savePhoneNumber(PhoneNumber phoneNumber);
  public void saveUserLogin(UserLogin userLogin, String securityGroupName);
  public void savePerson(Person person);
  public void saveCompany(Company company);
  public List<String> getListOfPersonRole();
  public List<String> getListOfCompanyRole();
 // public List<CountryCodeDTO> getListOfCountryCodes(); 
  public void savePrivateGroup(PrivateGroup privateGroup);
  public Person getPersonFromUsername(String username);
  public void updatePrivateGroup(PrivateGroup oldPrivateGroup, PrivateGroup updatedPrivateGroup);
  public void deletePrivateGroup(PrivateGroup privateGroup);
  public Person getPersonFromPhoneNumberUsername(String phonenumber);
  public Party getParty(Long id);
  public PrivateGroup getPrivateGroup(Long id);
  public void deletePerson(Person person);
  public Person getPerson(Long id);
  
  
}
