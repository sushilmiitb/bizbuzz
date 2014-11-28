package com.bizbuzz.service;

import java.util.List;
import java.util.Map;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.RegisterDevice;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.model.Connection.ConnectionType;

public interface ConnectionService {
  public Connection getConnection(Party fromParty, Party toParty);
  public void createConnection(Party fromParty, Party toParty, ConnectionType connectionType);
  public void deleteConnection(Party fromParty, Party toParty);
  public List<PrivateGroup> getPrivateGroupsByGroupOnwer(Person person);
  public PrivateGroup getPrivateGroupByPersonAndPrivateGroupId(Person person, Long groupId);
  public void deletePrivateGroupGroupMembersConnection(PrivateGroup privateGroup);
  public List<Person> getAllSellersConnections(Person seller);
  public List<RegisterDevice> getRegisterDeviceOfPersonOfSellerConnections(Person seller); 
  public List<Connection> getAllBuyersConnection(Person buyer);
  public PrivateGroup getPrivateGroupByGroupOwnerAndGroupMember(Party groupOwner, Party groupMember);
  public PrivateGroup getPrivateGroupByGroupOwnerAndGroupMemberWithToParties(Party groupOwner, Party groupMember);
  public List<Person> getGroupMembersByPrivateGroup(PrivateGroup privateGroup);
  public Person getBuyerBySellerAndBuyerId(Person seller, Long buyerId);
  public Person getSellerByBuyerAndSellerId(Person buyer,Long sellerId);
  public List<Connection> getAllSellerConnectionsUsingPrivateGroup(Person seller);
  public void flush();
  public Map<Long, PrivateGroup> convertToMap(List<PrivateGroup> list);
}
