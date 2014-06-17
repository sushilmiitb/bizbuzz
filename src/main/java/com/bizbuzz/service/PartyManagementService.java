package com.bizbuzz.service;

import org.springframework.ui.Model;

import com.bizbuzz.dto.PersonRegistrationDTO;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.UserLogin;


public interface PartyManagementService {
  public void getPersonRegistrationForm(Model m);
  public void savePersonRegistrationForm(Person person, Company company, PhoneNumber phoneNumber, UserLogin userLogin);
  public void savePersonRegistrationForm(PersonRegistrationDTO personRegistration);
}
