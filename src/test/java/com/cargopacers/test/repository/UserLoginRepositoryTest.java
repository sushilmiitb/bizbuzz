package com.cargopacers.test.repository;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizbuzz.model.SecurityGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.SecurityGroupRepository;
import com.bizbuzz.repository.UserLoginRepository;

import static org.junit.Assert.*;

/**
 *  
 *  @author mukesh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/*.xml")
@Transactional
public class UserLoginRepositoryTest {

  @Autowired
  private UserLoginRepository userLoginRepository;
  @Autowired
  private SecurityGroupRepository securityGroupRepository;

  @Test
  public void testSave() {
    UserLogin login = new UserLogin();
    login.setEmail("mukesh@abc.com");
    login.setPasswordHash("passwordhash");
    login.setEnabled(true);
    
    SecurityGroup group = new SecurityGroup();
    group.setName("s2");
    group.addAuthorities(Arrays.asList("a1", "a2", "a3", "a4"));
    
    login.addSecurityGroup(group);
    
    securityGroupRepository.save(group);
    userLoginRepository.save(login);
    
    assertNotNull(login.getId());

    UserLogin savedLogin = userLoginRepository.findOne(login.getId());

    assertEquals(login.getId(), savedLogin.getId());
    assertEquals(1, savedLogin.getSecurityGroups().size());
    
    userLoginRepository.delete(savedLogin);
    
    UserLogin deletedLogin = userLoginRepository.findOne(login.getId());
    assertNull(deletedLogin);
  }
}
