package com.bizbuzz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.CompanyRepository;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.repository.PartyRepository;
import com.bizbuzz.repository.PersonRepository;
import com.bizbuzz.repository.PrivateGroupRepository;

@Service
public class ConnectionServiceImpl implements ConnectionService {
  @Autowired
  ConnectionRepository connectionRepository;
  
  @Autowired
  PersonRepository personRepository;
  
  @Autowired
  CompanyRepository companyRepository;
  
  @Autowired
  PrivateGroupRepository privateGroupRepository;
  
  @Autowired
  PartyRepository partyRepository;
  
  public Connection getConnection(Party fromParty, Party toParty){
    return connectionRepository.findByFromPartyIdAndToPartyId(fromParty.getId(), toParty.getId());
  }
  
  public void createConnection(Party fromParty, Party toParty, ConnectionType connectionType){
    Connection connection = new Connection(fromParty, toParty, connectionType);
    connectionRepository.save(connection);
    fromParty.addToParty(connection);
    toParty.addFromParty(connection);
    //partyRepository.save(fromParty);
    //partyRepository.save(toParty);
  }
  
  public void deleteConnection(Party fromParty, Party toParty){
    Connection connection = connectionRepository.findByFromPartyIdAndToPartyId(fromParty.getId(), toParty.getId());
    if(connection == null){
      /**
       * Connection doesn't exist. Assume that connection is deleted
       */
      return;
    }
    fromParty.deleteToParty(toParty.getId());
    toParty.deleteFromParty(fromParty.getId());
    connectionRepository.deleteById(fromParty.getId(), toParty.getId());;
  }
  
  public void deletePrivateGroupGroupMembersConnection(PrivateGroup privateGroup){
    connectionRepository.deleteAllToPartyConnectionById(privateGroup.getId());
  }
  
  public List<PrivateGroup> getPrivateGroupsByGroupOnwer(Person person){
    if(person==null){
      return null;
    }
    return connectionRepository.findPrivateGroupByFromPartyIdAndConnectionTypeOrderByPrivateGroupName(person.getId(), ConnectionType.GROUPOWNER_GROUP);
  }
  
  public PrivateGroup getPrivateGroupByPersonAndPrivateGroupId(Person person, Long privateGroupId){
    return connectionRepository.findPrivateGroupByFromPartyIdAndConnectionTypeAndId(person.getId(), ConnectionType.GROUPOWNER_GROUP, privateGroupId);
  }

  public List<Person> getAllSellersConnections(Person seller){
    return connectionRepository.findPersonByFromPartyIdAndConnectionTypeOrderByFirstName(seller.getId(), ConnectionType.SELLER_BUYER);
  }
  
  public List<Connection> getAllBuyersConnection(Person buyer) {
    return connectionRepository.findPersonByToPartyIdAndConnectionTypeOrderByFirstName(buyer.getId(), ConnectionType.SELLER_BUYER);
  }
  
  public List<Connection> getAllSellerConnectionsUsingPrivateGroup(Person seller){
    return connectionRepository.findConnectionsByFromPartyIdOrderAndConnectionTypeByToPartyFirstName(seller.getId(), ConnectionType.GROUPOWNER_GROUP);
  }
  
  public PrivateGroup getPrivateGroupByGroupOwnerAndGroupMember(Party groupOwner, Party groupMember){
    return connectionRepository.findPrivateGroupByFromPartyIdAndToPartyId(groupOwner.getId(), groupMember.getId());
  }
  
  public List<Person> getGroupMembersByPrivateGroup(PrivateGroup privateGroup){
    return connectionRepository.findPersonByFromPartyIdAndConnectionTypeOrderByFirstName(privateGroup.getId(), ConnectionType.GROUP_MEMBERS);
  }
  
  public Person getBuyerBySellerAndBuyerId(Person seller, Long buyerId){
    return connectionRepository.findPersonByFromPartyIdAndId(seller.getId(), buyerId);
  }
  
  public Person getSellerByBuyerAndSellerId(Person buyer, Long sellerId) {
    return connectionRepository.findPersonByToPartyIdAndId(buyer.getId(), sellerId);
  }
  
  public void flush(){
    connectionRepository.flush();
  }
  
  public Map<Long, PrivateGroup> convertToMap(List<PrivateGroup> list){
    Map<Long, PrivateGroup> map = new LinkedMap();
    for(int i=0; i<list.size(); i++){
      map.put(list.get(i).getId(), list.get(i));
    }
    return map;
  }
  
}
