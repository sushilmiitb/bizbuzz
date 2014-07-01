package com.bizbuzz.service;

import java.util.List;

import org.springframework.ui.Model;

import com.bizbuzz.dto.PersonRegistrationDTO;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.UserLogin;


public interface PartyManagementService {
  public void savePhoneNumber(PhoneNumber phoneNumber);
  public void saveUserLoginWithSecurityGroup(UserLogin userLogin, String securityGroupName);
  public void savePersonWithUserName(Person person, UserLogin userLogin);
  public void saveCompany(Company company);
  public List<String> getListOfPersonRole();
  public List<String> getListOfCompanyRole();
  public void savePrivateGroup(PrivateGroup privateGroup);
  public Person getPersonFromUsername(String username);
}
