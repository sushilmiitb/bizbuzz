package com.bizbuzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.repository.CompanyRepository;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.repository.PersonRepository;

@Service
public class ConnectionServiceImpl implements ConnectionService {
  @Autowired
  ConnectionRepository connectionRepository;
  
  @Autowired
  PersonRepository personRepository;
  
  @Autowired
  CompanyRepository companyRepository;
  
  public void createConnection(Person person, Company company, ConnectionType connectionType){
    Connection connection = new Connection(company, person, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    company.addToParty(connection);
    person.addFromParty(connection);
    personRepository.save(person);
    companyRepository.save(company);
  }
}
