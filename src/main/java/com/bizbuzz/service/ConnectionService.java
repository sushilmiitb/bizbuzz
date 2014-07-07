package com.bizbuzz.service;

import java.util.List;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.model.Connection.ConnectionType;

public interface ConnectionService {
  public Connection getConnection(Party fromParty, Party toParty);
  public void createConnection(Party fromParty, Party toParty, ConnectionType connectionType);
  public void deleteConnection(Party fromParty, Party toParty);
  public void createCompanyPersonConnection(Company company, Person person);
  public void createPersonOwnerPrivateGroupConnection(Person person, PrivateGroup privateGroup);
  public List<PrivateGroup> getListOfPrivateGroupsFromPerson(Person person);
  public PrivateGroup getPrivateGroupFromPerson(Person person, String privateGroupName);
  public void deletePersonOwnerPrivateGroupConnection(Person person, PrivateGroup privateGroup);
  public List<Person> getAllSellerConnections(Person seller);
  public PrivateGroup getPrivateGroupByGroupOwnerAndGroupMember(Party groupOwner, Party groupMember);
}
