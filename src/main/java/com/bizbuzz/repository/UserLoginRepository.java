package com.bizbuzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bizbuzz.model.UserLogin;

/**
 * 
 *  @author mukesh
 */
@Repository
@Transactional(readOnly=true)
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
  
  public UserLogin getByUsername(String username);

}
