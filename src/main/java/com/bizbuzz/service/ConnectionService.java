package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.model.Connection.ConnectionType;

public interface ConnectionService {
  public void createCompanyPersonConnection(Company company, Person person);
  public void createPersonOwnerPrivateGroupConnection(Person person, PrivateGroup privateGroup);
  public List<PrivateGroup> getListOfPrivateGroupsFromPerson(Person person);
}
