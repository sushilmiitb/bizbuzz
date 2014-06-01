package com.bizbuzz.service.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.SecurityGroup;
import com.bizbuzz.model.UserLogin;
import com.bizbuzz.repository.UserLoginRepository;

/**
 * 
 * @author mukesh
 *
 */
@Service(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserLoginRepository userLoginRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserLogin userLogin = userLoginRepository.getByUsername(username);
    
    if(userLogin == null) {
      throw new UsernameNotFoundException(username);
    }
    
    Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    Collection<SecurityGroup> securityGroups = userLogin.getSecurityGroups();
    
    for(SecurityGroup sg: securityGroups) {
      for(String authority: sg.getAuthorities()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(authority));
      }
    }
    
    User user = new User(userLogin.getUsername(), userLogin.getPasswordHash(), userLogin.isEnabled(),
         userLogin.isEnabled(), userLogin.isEnabled(), userLogin.isEnabled(), grantedAuthorities);
    
    return user;
  }

}
