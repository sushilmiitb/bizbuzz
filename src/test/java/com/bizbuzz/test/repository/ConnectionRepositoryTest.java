package com.bizbuzz.test.repository;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.repository.ConnectionRepository;
import com.bizbuzz.repository.PartyRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/*.xml")
@Transactional
public class ConnectionRepositoryTest {
  @Autowired
  ConnectionRepository connectionRepository;
  @Autowired
  PartyRepository partyRepository;
  
  @Test
  public void testSave(){
    Person person = new Person();
    Company company = new Company();
    
    person.setFirstName("Shaunak");
    company.setCompanyName("ABC sarees");
    
    partyRepository.save(person);
    partyRepository.save(company);
    
    Person savedPerson = (Person)partyRepository.findOne(person.getId());
    Company savedCompany = (Company)partyRepository.findOne(company.getId());
    Connection connection = new Connection(savedCompany, savedPerson, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    
    savedPerson.addFromParty(connection);
    savedCompany.addToParty(connection);
    partyRepository.save(savedPerson);
    partyRepository.save(savedCompany);
    
    assertEquals("Asserting size of connection table", 1, connectionRepository.findAll().size());
    assertEquals("Asserting toparty id from connection method",savedPerson.getId(), connectionRepository.findAll().get(0).getToParty().getId());
    
    assertEquals("Asserting from party id through connection retrieval",savedCompany.getId(),connectionRepository.findAll().get(0).getFromParty().getId());
    
    savedCompany = (Company)partyRepository.findOne(company.getId());
    assertEquals("Asserting size of toParties", 1, savedCompany.getToParties().size());
    assertEquals("Asserting id of to party",savedPerson.getId(),savedCompany.getToParties().get(0).getToParty().getId());
    
    savedPerson = (Person)partyRepository.findOne(person.getId());
    assertEquals("Asserting size of fromParties",1,savedPerson.getFromParties().size());
    assertEquals("Asserting id  of fromParty",savedCompany.getId(),savedPerson.getFromParties().get(0).getFromParty().getId());
    
  }
  
  @Test
  public void testDuplicateSave(){
    Person person = new Person();
    Company company = new Company();
    
    person.setFirstName("Shaunak");
    company.setCompanyName("ABC sarees");
    
    partyRepository.save(person);
    partyRepository.save(company);
    
    Person savedPerson = (Person)partyRepository.findOne(person.getId());
    Company savedCompany = (Company)partyRepository.findOne(company.getId());
    Connection connection = new Connection(savedCompany, savedPerson, ConnectionType.COMPANY_PERSON);
    connectionRepository.save(connection);
    
    savedPerson.addFromParty(connection);
    savedCompany.addToParty(connection);
    partyRepository.save(savedPerson);
    partyRepository.save(savedCompany);
    
    Connection newConnection = new Connection(savedCompany, savedPerson, ConnectionType.COMPANY_PERSON);
    
    savedPerson.addFromParty(newConnection);
    savedCompany.addToParty(newConnection);
    partyRepository.save(savedPerson);
    partyRepository.save(savedCompany);
    connectionRepository.save(newConnection);
    /*
    TestUtils tu = new TestUtils();
    System.out.println("test");
    tu.testing(savedCompany.getId());*/
    
//    assertEquals("Asserting number of connection", 1, connectionRepository.findAll().size());
//    assertEquals("Asserting number of toParties", 1, partyRepository.findOne(savedCompany.getId()).getToParties().size());
//    assertEquals("Asserting number of fromParties", 1, partyRepository.findOne(savedPerson.getId()).getFromParties().size());
  }

}
