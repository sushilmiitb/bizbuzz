package com.bizbuzz.test.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizbuzz.model.Address;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PhoneNumber;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.PhoneNumber.PhoneType;
import com.bizbuzz.repository.PartyRepository;
import com.bizbuzz.utils.HelperFunctions;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/*.xml")
@Transactional
public class PartyRepositoryTest {
  @Autowired
  private PartyRepository partyRepository;
  
  @Test
  public void testSave(){
    Person person = new Person();
    /**
     * Setting and testing addresses
     */
    List<Address> addresses = new ArrayList<Address>();
    for(int i=0;i<5;i++){
      Address address = new Address();
      address.setAddressLine1("Address Line 1. Record:" + i);
      address.setAddressLine2("Address Line 2. Record:" + i);
      address.setAddressType(Address.AddressType.OFFICE);
      address.setCity("Surat");
      address.setLandmark(null);
      addresses.add(address);
    }
    person.setAddresses(addresses);
    
    /**
     * Setting bio data details
     */
    String firstName = "Shaunak";
    person.setFirstName(firstName);
    String lastName = "Chhaparia";
    person.setLastName(lastName);
    char gender = 'm';
    person.setGender(gender);
    String personRole = "Co Owner";
    person.setPersonRole(personRole);
    
    /**
     * Setting and testing phonenumbers
     */
    List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    for (int i=0;i<3;i++){
      PhoneNumber phoneNumber = new PhoneNumber();
      phoneNumber.setContactNumber("9099043920"+i);
      phoneNumber.setPhoneType(PhoneType.MOBILE);
      phoneNumbers.add(phoneNumber);
    }
    person.setPhoneNumbers(phoneNumbers);
    
    partyRepository.save(person);
    
    assertNotNull(person.getId());
    
    Person savedPerson = (Person) partyRepository.findOne(person.getId());
    
    assertNotNull("Person.testSave: Id of person saved null", savedPerson.getId());
    
    /**
     * asserting address save
     */
    assertNotNull(savedPerson.getAddresses());
    assertNotNull(savedPerson.getAddresses().get(0));
    assertEquals(addresses.size(), savedPerson.getAddresses().size());
    assertEquals("Person.testSave: Address check", addresses.get(0).getAddressLine1(), savedPerson.getAddresses().get(0).getAddressLine1());
    
    /**
     * asserting bio data
     */
    assertEquals(personRole, savedPerson.getPersonRole());
    assertEquals("Person.testSave: Person Role check",firstName, savedPerson.getFirstName());
    
    /**
     * asserting phone Numbers
     */
    assertNotNull(savedPerson.getPhoneNumbers());
    assertNotNull(savedPerson.getPhoneNumbers().get(0));
    assertEquals(phoneNumbers.size(), savedPerson.getPhoneNumbers().size());
    assertEquals(phoneNumbers.get(0).getContactNumber(), savedPerson.getPhoneNumbers().get(0).getContactNumber());
    System.out.println("phone number:"+savedPerson.getPhoneNumbers().get(0).getContactNumber());
    assertEquals("Person.testSave: Phone number check", phoneNumbers.get(0).getPhoneType(), savedPerson.getPhoneNumbers().get(0).getPhoneType());
    
    /**
     * asserting deletion
     */
    partyRepository.delete(person.getId());
    assertNull(partyRepository.findOne(person.getId()));
  }
  
  @Test
  public void testPersonToPersonConnection(){
    /*
     * Creating a few Persons
     */
    String firstName[]={"Shaunak", "Sushil", "Radhika", "Hardik"};
    List<String> personRoles = new ArrayList<String>();
    personRoles.add("Employee");
    personRoles.add("Co Owner");
    personRoles.add("Promoter");
    personRoles.add("Solo Owner");
    List<Person> persons = new ArrayList<Person>();
    System.out.println("before");
    for(int i=0;i<4;i++){
      System.out.println("inside for"+i);
      Person p = new Person();
      p.setFirstName(firstName[i]);
      p.setPersonRole(personRoles.get(i));
      persons.add(p);
    }
    persons.get(0).addToParty(persons.get(1), ConnectionType.SELLER_BUYER);
    persons.get(0).addToParty(persons.get(3), ConnectionType.SELLER_BUYER);
    
    partyRepository.save(persons);
    
    /*
     * Retrieving saved persons 
     */
    List<Person> savedPersons = new ArrayList<Person> ();
    List<Party> savedParty = partyRepository.findAll();
    for(int i=0; i<savedParty.size();i++){
      savedPersons.add((Person)savedParty.get(i));
    }
    assertNotNull("Person.testConnection:Asserting that toParty gets added", savedPersons.get(0).getToParties());
    assertNotNull("Person.testConenction:Asserting that From party gets added to the to party", savedPersons.get(0).getToParties().get(0).getToParty().getFromParties());
    assertEquals("Person.testConnection:Asserting size of toParties added", savedPersons.get(0).getToParties().size(), 2);
    assertEquals("Person.testConnection:", ((Person)savedPersons.get(0).getToParties().get(0).getToParty()).getFirstName(), persons.get(1).getFirstName());
    
    /**
     * Testing deletion of connections
     */
    
  }
  
  @Test
  public void testUpdateParty(){
    Company company = new Company();
    company.setCompanyName("ABC sarees");
    company.setCompanyRegistrationType(Company.CompanyRegistrationType.PVT_LTD);
    company.setCompanyRole(Company.CompanyRole.SELLER);
    
    Person person1 = new Person();
    person1.setFirstName("Shaunak");
    person1.setPersonRole("Employee");
    
    Person person2 = new Person();
    person2.setFirstName("Bimal");
    person2.setPersonRole("Promoter");
    
    partyRepository.save(company);
    partyRepository.save(person1);
    partyRepository.save(person2);
    
    Company savedCompany = (Company)partyRepository.findOne(company.getId());
    savedCompany.setCompanyName("Radhika Sarees");
    partyRepository.save(savedCompany);
    Company newCompany = (Company)partyRepository.findOne(savedCompany.getId());
    assertEquals(savedCompany.getCompanyName(), newCompany.getCompanyName());
    
  }
  
  @Test
  public void testCascadeSave(){
    Person person1 = new Person();
    Person person2 = new Person();
    
    person1.setFirstName("Shaunak");
    person2.setFirstName("Sushil");
    
    person1.addToParty(person2, ConnectionType.SELLER_BUYER);
    partyRepository.save(person1);
    
    Person savedPerson1 = (Person)partyRepository.findOne(person1.getId());

    assertEquals("Asserting that saving has actually cascaded", person2.getFirstName(),((Person)savedPerson1.getToParties().get(0).getToParty()).getFirstName());
  }
  
  @Test
  public void testCascadeDelete(){
    Person person1 = new Person();
    Person person2 = new Person();
    
    person1.setFirstName("Shaunak");
    person2.setFirstName("Sushil");
    person1.addToParty(person2, ConnectionType.SELLER_BUYER);
    partyRepository.save(person1);
    partyRepository.save(person2);
    
    Person savedPerson1 = (Person)partyRepository.findOne(person1.getId());
    partyRepository.delete(((Person)savedPerson1.getToParties().get(0).getToParty()).getId());
    
    assertEquals(1, partyRepository.findAll().size());
    assertEquals(1, partyRepository.findOne(person1.getId()).getToParties().size());
  }
}
