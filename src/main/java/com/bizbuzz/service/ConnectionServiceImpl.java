package com.bizbuzz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.CompanyRepository;
import com.bizbuzz.repository.ConnectionRepository;
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
  
  public void createCompanyPersonConnection(Company company, Person person){
    Connection connection = new Connection(company, person, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    company.addToParty(connection);
    person.addFromParty(connection);
    personRepository.save(person);
    companyRepository.save(company);
  }
  
  public void createPersonOwnerPrivateGroupConnection(Person person, PrivateGroup privateGroup){
    Connection connection = new Connection(person, privateGroup, ConnectionType.GROUPOWNER_GROUP);
    connectionRepository.save(connection);
    person.addToParty(connection);
    privateGroup.addFromParty(connection);
    personRepository.save(person);
    privateGroupRepository.save(privateGroup);
  }
  
  public List<PrivateGroup> getListOfPrivateGroupsFromPerson(Person person){
    List <Connection> connections = connectionRepository.findByFromPartyIdAndConnectionType(person.getId(), ConnectionType.GROUPOWNER_GROUP);
    List<PrivateGroup> privateGroups = new ArrayList<PrivateGroup>(connections.size());
    for(int i=0; i<connections.size();i++){
      privateGroups.add((PrivateGroup)connections.get(i).getToParty());
    }
    return privateGroups;
  }
  
}
