package com.bizbuzz.test.repository;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bizbuzz.model.SecurityGroup;
import com.bizbuzz.repository.SecurityGroupRepository;

import static org.junit.Assert.*;

/**
 *  
 *  @author mukesh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/*.xml")
@Transactional
public class SecurityGroupRepositoryTest {
  
  @Autowired
  private SecurityGroupRepository securityGroupRepository;

  @Test
  public void testSave() {
    SecurityGroup group = new SecurityGroup();
    group.setName("s1");
    group.addAuthorities(Arrays.asList("a1", "a2", "a3", "a4"));
    securityGroupRepository.save(group);
    
    assertNotNull(group.getId());
    
    SecurityGroup savedGroup = securityGroupRepository.findOne(group.getId());
    assertEquals(group.getAuthorities().size(), savedGroup.getAuthorities().size());
    
    securityGroupRepository.delete(savedGroup);
    SecurityGroup deletedGroup = securityGroupRepository.findOne(group.getId());
    assertNull(deletedGroup); 
  }
  
}