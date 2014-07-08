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
  public List<PrivateGroup> getPrivateGroupByGroupOnwer(Person person);
  public PrivateGroup getPrivateGroupByPersonAndPrivateGroupId(Person person, Long groupId);
  public void deletePrivateGroupGroupMembersConnection(PrivateGroup privateGroup);
  public List<Person> getAllSellerConnections(Person seller);
  public PrivateGroup getPrivateGroupByGroupOwnerAndGroupMember(Party groupOwner, Party groupMember);
  public List<Person> getGroupMembersByPrivateGroup(PrivateGroup privateGroup);
  public Person getBuyerBySellerAndBuyerId(Person seller, Long buyerId);
  public void flush();
}
