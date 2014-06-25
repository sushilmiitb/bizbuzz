package com.bizbuzz.test.repository;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.UserLoginRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml", "classpath:META-INF/spring/datasource-beans.xml", "classpath:META-INF/spring/securityContext.xml"})
@Transactional
public class UserLoginTest {
  @Autowired
  UserLoginRepository userLoginRepository;
  
  @Test
  public void testFindById(){
    UserLogin ul = new UserLogin();
    ul.setId("hello");
    userLoginRepository.save(ul);
    UserLogin userLogin = (UserLogin)userLoginRepository.findById("hello");
    assertNotNull(userLogin);
    assertEquals (ul.getId(), userLogin.getId());
  }
}
