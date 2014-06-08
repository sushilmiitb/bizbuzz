package com.bizbuzz.test.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.repository.PartyRepository;

@ContextConfiguration("classpath:META-INF/spring/*.xml")
public class TestUtils {
  @Autowired
  PartyRepository partyRepository;
  
  public void testing(Long id){
    System.out.println("test1");
    assertNotNull(partyRepository);
    List<Party> parties = partyRepository.findAll();
    System.out.println("test2");
    Company company = (Company)parties.get(1);
    System.out.println("test3");
    Person person = (Person)parties.get(0);
    System.out.println("test4");
    System.out.println("testin"+company.getCompanyName()+person.getFirstName());
    assertEquals("Asserting number of toParties", 1, partyRepository.findOne(id).getToParties().size());
  }
}
