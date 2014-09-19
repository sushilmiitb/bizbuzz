package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.ChatRoom;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.UserLogin;


@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long>{
 
}
